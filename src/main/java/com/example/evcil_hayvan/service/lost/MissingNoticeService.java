package com.example.evcil_hayvan.service.lost;

import com.example.evcil_hayvan.dto.create.lost.CreateMissingNoticeDto;
import com.example.evcil_hayvan.dto.get.GetMissingNoticeDto;
import com.example.evcil_hayvan.dto.update.lost.UpdateMissingNoticeDto;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.lost.MissingNotice;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.PetRepo;
import com.example.evcil_hayvan.repository.lost.MissingNoticeRepo;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MissingNoticeService {

    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;
    private final PetService petService;
    private final MissingNoticeRepo missingNoticeRepo;

    public MissingNoticeService(OwnerRepo ownerRepo, PetRepo petRepo,
                                PetService petService, MissingNoticeRepo missingNoticeRepo) {
        this.ownerRepo = ownerRepo;
        this.petRepo = petRepo;
        this.petService = petService;
        this.missingNoticeRepo = missingNoticeRepo;
    }

    public MissingNotice getMissingNoticeById(Long missinNoticeId){
        MissingNotice missingNotice = missingNoticeRepo.findMissingNoticeById(missinNoticeId)
                .orElseThrow(() -> new EntityNotFoundException("Kayıp ilanı bulunamadı."));

        return missingNotice;
    }

    public List<MissingNotice> getMissingNoticesByOwnerId(Long ownerId){
        List<MissingNotice> missingNotices = missingNoticeRepo.findMissingNoticeByOwner_OwnerId(ownerId);
        return missingNotices;
    }

    public List<MissingNotice> getMissingNoticesByPetId(Long petId){
        List<MissingNotice> missingNotices = missingNoticeRepo.findMissingNoticeByPetId(petId);
        return missingNotices;
    }

    public MissingNotice createMissingNotice(CreateMissingNoticeDto dto){
        Pet pet = petService.getPetById(dto.getPetId());
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(pet.getLostStatus().equals(true)){
            throw new IllegalStateException(pet.getPetName() + " zaten kayıp olarak işaretlenmiş.");
        }

        MissingNotice missingNotice = new MissingNotice(pet.getPetId(),
                dto.getLastSeenLocation(), dto.getLastSeenTime(), dto.getDescription());

        pet.setLostStatus(true);
        petRepo.save(pet);

        return missingNoticeRepo.save(missingNotice);
    }

    public MissingNotice foundMissingNotice(GetMissingNoticeDto dto){
        MissingNotice missingNotice = getMissingNoticeById(dto.getMissingNoticeId());

        Pet pet = petService.getPetById(missingNotice.getPetId());

        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(!(pet.getLostStatus().equals(true))){
            throw new IllegalStateException(pet.getPetName() + " kayıp durumda değil.");
        }else{
            pet.setLostStatus(false);
            missingNotice.setFound(true);
            missingNotice.setFoundTime(LocalDateTime.now());
        }
        petRepo.save(pet);
        return missingNoticeRepo.save(missingNotice);
    }

    public MissingNotice updateMissingNotice(UpdateMissingNoticeDto dto){

        MissingNotice missingNotice = getMissingNoticeById(dto.getMissingNoticeId());

        Pet pet = petService.getPetById(missingNotice.getPetId());

        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(!(pet.getLostStatus().equals(true))){
            throw new IllegalStateException(pet.getPetName() + " kayıp durumda değil.");
        }else{
            if(dto.getLastSeenLocation() != null && dto.getLastSeenLocation().trim() != ""){
                missingNotice.setLastSeenLocation(dto.getLastSeenLocation().trim());
            }
            if(dto.getLastSeenTime() != null){
                missingNotice.setLastSeenTime(dto.getLastSeenTime());
            }
            if(dto.getDescription() != null && dto.getDescription().trim() != ""){
                missingNotice.setDescription(dto.getDescription().trim());
            }
        }

        return missingNoticeRepo.save(missingNotice);

    }


    public void deleteMissingNotice(GetMissingNoticeDto dto){
        MissingNotice missingNotice = getMissingNoticeById(dto.getMissingNoticeId());

        Pet pet = petService.getPetById(missingNotice.getPetId());

        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }
        if(!(pet.getLostStatus().equals(true))){
            throw new IllegalStateException(pet.getPetName() + " kayıp durumda değil.");
        }else{
            pet.setLostStatus(false);
            petRepo.save(pet);
            missingNoticeRepo.delete(missingNotice);
        }

    }

}
