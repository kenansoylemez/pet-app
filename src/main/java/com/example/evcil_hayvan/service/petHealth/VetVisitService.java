package com.example.evcil_hayvan.service.petHealth;

import com.example.evcil_hayvan.dto.create.health.CreateVetVisitDto;
import com.example.evcil_hayvan.dto.delete.health.DeleteVetVisitDto;
import com.example.evcil_hayvan.dto.get.GetAllByPetDto;
import com.example.evcil_hayvan.dto.update.health.UpdateVetVisitDtoOwnerPet;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.petHealth.VetVisit;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.petHealth.VetVisitRepo;
import com.example.evcil_hayvan.repository.PetRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VetVisitService {

    private final VetVisitRepo vetVisitRepo;
    private final PetRepo petRepo;
    private final PetService petService;
    private final OwnerRepo ownerRepo;
    private final OwnerService ownerService;

    public VetVisitService(VetVisitRepo vetVisitRepo, PetRepo petRepo, PetService petService, OwnerRepo ownerRepo, OwnerService ownerService) {
        this.vetVisitRepo = vetVisitRepo;
        this.petRepo = petRepo;
        this.petService = petService;
        this.ownerRepo = ownerRepo;
        this.ownerService = ownerService;
    }

    public VetVisit findVetVisitById(Long vetVisitId){
        VetVisit vetVisit = vetVisitRepo.findById(vetVisitId)
                .orElseThrow(() -> new EntityNotFoundException("Veteriner ziyareti  bulunamadı: ID = " +  vetVisitId));
        return vetVisit;
    }

    public List getAllVetVisitsByPetId(GetAllByPetDto dto){
        Pet pet = petService.getPetById(dto.getPetId());
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }
        List<VetVisit> allVetVisits = vetVisitRepo.findAllByPetPetId(pet.getPetId());
        return allVetVisits;
    }


    public VetVisit createVetVisit(CreateVetVisitDto dto){
        Pet pet = petService.getPetById(dto.getPetId());
        if(pet.getOwner().getOwnerId().equals(dto.getOwnerId())){
            VetVisit vetVisit = new VetVisit(
                    dto.getVetVisitCause(),
                    dto.getVetVisitDate(),
                    pet,
                    dto.getVetVisitResult()
            );
            if(vetVisit.getVetVisitResult() == null){
                vetVisit.setVetVisitResult("Belirtilmedi");
            }

            return vetVisitRepo.save(vetVisit);
        }
        throw new WrongOwnerException();
    }

    public void deleteVetVisit(DeleteVetVisitDto dto){
        VetVisit vetVisit = findVetVisitById(dto.getVetVisitId());
        Pet pet = vetVisit.getPet();
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }
        vetVisitRepo.delete(vetVisit);
    }

    public VetVisit updateVetVisit(UpdateVetVisitDtoOwnerPet dto){
        VetVisit vetVisit = findVetVisitById(dto.getVetVisitId());
        Pet pet = vetVisit.getPet();
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }
        if(dto.getNewVetVisitCause() != null && !(vetVisit.getVetVisitCause().equals(dto.getNewVetVisitCause()))){
            vetVisit.setVetVisitCause(dto.getNewVetVisitCause());
        }
        if(dto.getNewVetVisitDate() != null && !(vetVisit.getVetVisitDate().equals(dto.getNewVetVisitDate()))){
            vetVisit.setVetVisitDate(dto.getNewVetVisitDate());
        }
        if(dto.getNewVetVisitResult() != null && !(vetVisit.getVetVisitResult().equals(dto.getNewVetVisitResult()))){
            vetVisit.setVetVisitResult(dto.getNewVetVisitResult());
        }
        return vetVisitRepo.save(vetVisit);
    }

}
