package com.example.evcil_hayvan.service.lost;

import com.example.evcil_hayvan.dto.update.pet.lost.CreateLostAdvertDto;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.lost.MissingNotice;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.PetRepo;
import com.example.evcil_hayvan.repository.lost.LostPetRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class LostPetService {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetRepo petRepo;
    private final LostPetRepo lostPetRepo;

    public LostPetService(PetService petService, OwnerService ownerService, PetRepo petRepo, LostPetRepo lostPetRepo) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petRepo = petRepo;
        this.lostPetRepo = lostPetRepo;
    }

    public MissingNotice createLostAdvert(CreateLostAdvertDto dto){
        Pet pet = petService.getPetById(dto.getPetId());
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        MissingNotice missingNotice = new MissingNotice(pet.getPetId(), dto.getLastSeenLocation(), dto.getLastSeenTime(), dto.getDescription());
        pet.setLost(true);
        petRepo.save(pet);
        return lostPetRepo.save(missingNotice);

    }

}
