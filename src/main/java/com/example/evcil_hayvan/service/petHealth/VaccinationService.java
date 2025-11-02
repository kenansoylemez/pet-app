package com.example.evcil_hayvan.service.petHealth;

import com.example.evcil_hayvan.dto.create.VaccinationCreateDto;
import com.example.evcil_hayvan.dto.delete.DeleteVaccinationDto;
import com.example.evcil_hayvan.dto.get.GetAllByPetDto;
import com.example.evcil_hayvan.dto.update.UpdateVaccinationDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.petHealth.VaccinationRepo;
import com.example.evcil_hayvan.repository.pets.PetRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccinationService {

    private final PetRepo petRepo;
    private final VaccinationRepo vaccinationRepo;
    private final OwnerRepo ownerRepo;
    private final PetService petService;
    private final OwnerService ownerService;

    public VaccinationService(PetRepo petRepo, VaccinationRepo vaccinationRepo, OwnerRepo ownerRepo, PetService petService, OwnerService ownerService) {
        this.petRepo = petRepo;
        this.vaccinationRepo = vaccinationRepo;
        this.ownerRepo = ownerRepo;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    public Vaccination getVaccinationById(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepo.findById(vaccinationId)
                .orElseThrow(() -> new EntityNotFoundException("Aşı bulunamadı: ID = " + vaccinationId));
        return vaccination;
    }

    public List getAllVaccinationsByPetId(GetAllByPetDto dto){
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        Pet pet = petService.findPetById(dto.getPetId());
        if(petService.isPetOwnerCorrect(pet, owner)){
            throw new WrongOwnerException();
        }
        List<Vaccination> allVaccinations = vaccinationRepo.findAllByPetPetId(pet.getPetId());
        return allVaccinations;
    }

    @Transactional
    public Vaccination createVaccination(VaccinationCreateDto dto){
        Pet pet =  petService.findPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());

        if(petService.isPetOwnerCorrect(pet, owner)){
            Vaccination vaccination = new Vaccination(
                    dto.getVaccineName(),
                    dto.getVaccinationDate(),
                    pet
            );
            return vaccinationRepo.save(vaccination);
        }
        throw new WrongOwnerException();
    }

    @Transactional
    public void deleteVaccination(DeleteVaccinationDto dto){
        Vaccination vaccination = getVaccinationById(dto.getVaccinationId());
        Pet pet = vaccination.getPet();
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(petService.isPetOwnerCorrect(pet, owner))){
            throw new WrongOwnerException();
        }
        vaccinationRepo.delete(vaccination);
    }

    public Vaccination updateVaccination(UpdateVaccinationDto dto){
        Vaccination vaccination = getVaccinationById(dto.getVaccinationId());
        Pet pet = vaccination.getPet();
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(petService.isPetOwnerCorrect(pet, owner))){
            throw new WrongOwnerException();
        }
        if(dto.getNewVaccineName() != null && !(vaccination.getVaccineName().equals(dto.getNewVaccineName()))){
            vaccination.setVaccineName(dto.getNewVaccineName());
        }
        if(dto.getNewVaccinationDate() != null && !(vaccination.getVaccinationDate().equals(dto.getNewVaccinationDate()))){
            vaccination.setVaccinationDate(dto.getNewVaccinationDate());
        }
        return vaccinationRepo.save(vaccination);
    }

}
