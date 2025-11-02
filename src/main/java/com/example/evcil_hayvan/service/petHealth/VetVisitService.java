package com.example.evcil_hayvan.service.petHealth;

import com.example.evcil_hayvan.dto.create.VetVisitCreateDto;
import com.example.evcil_hayvan.dto.delete.DeleteVetVisitDto;
import com.example.evcil_hayvan.dto.get.GetAllByPetDto;
import com.example.evcil_hayvan.dto.update.UpdateVetVisitDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import com.example.evcil_hayvan.entity.petHealth.VetVisit;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.petHealth.VetVisitRepo;
import com.example.evcil_hayvan.repository.pets.PetRepo;
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
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        Pet pet = petService.findPetById(dto.getPetId());
        if(petService.isPetOwnerCorrect(pet, owner)){
            throw new WrongOwnerException();
        }
        List<VetVisit> allVetVisits = vetVisitRepo.findAllByPetPetId(pet.getPetId());
        return allVetVisits;
    }


    public VetVisit createVetVisit(VetVisitCreateDto dto){
        Pet pet = petService.findPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(petService.isPetOwnerCorrect(pet, owner)){
            VetVisit vetVisit = new VetVisit(
                    dto.getVetVisitCause(),
                    dto.getVetVisitDate(),
                    pet,
                    dto.getVetVisitResult()
            );

            return vetVisitRepo.save(vetVisit);
        }
        throw new WrongOwnerException();
    }

    public void deleteVetVisit(DeleteVetVisitDto dto){
        VetVisit vetVisit = findVetVisitById(dto.getVetVisitId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        Pet pet = vetVisit.getPet();
        if(!(petService.isPetOwnerCorrect(pet, owner))){
            throw new WrongOwnerException();
        }
        vetVisitRepo.delete(vetVisit);
    }

    public VetVisit updateVetVisit(UpdateVetVisitDto dto){
        VetVisit vetVisit = findVetVisitById(dto.getVetVisitId());
        Pet pet = vetVisit.getPet();
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(petService.isPetOwnerCorrect(pet, owner))){
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
