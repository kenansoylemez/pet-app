package com.example.evcil_hayvan.service;

import com.example.evcil_hayvan.dto.delete.PetDeleteDto;
import com.example.evcil_hayvan.dto.update.ChangePetOwnerDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.PetRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PetService {

    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;
    private final OwnerService ownerService;

    public PetService(PetRepo petRepo, OwnerRepo ownerRepo, OwnerService ownerService) {
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
        this.ownerService = ownerService;
    }

    public double calculatePetAge(LocalDate petBirthDate){
        int year = petBirthDate.getYear();
        int month = petBirthDate.getMonthValue();
        LocalDate today = LocalDate.now();
        int ageYear = today.getYear() - year;
        int ageMonth = today.getMonthValue() - month;
        int yearDifferenceInMonths = ageYear * 12;
        int totalMonthDifference = ageMonth + yearDifferenceInMonths;
        int plusYears = totalMonthDifference / 12;
        int plusMonths = totalMonthDifference % 12;
        double fraction;
        double finalAge;
        if(plusMonths >= 6){
            fraction = 0.5D;
        }else{
            fraction = 0D;
        }
        finalAge = plusYears +  fraction;
        return finalAge;
    }

    public Pet findPetById(Long petId){
        return petRepo.findPetByPetId(petId)
                .orElseThrow(()->new EntityNotFoundException("Evcil hayvan bulunamadı: ID = " + petId));
    }

    public List<Pet> findAllPetsByOwnerId(Long ownerId){
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: ID = " + ownerId));
        return petRepo.findPetsByOwner_OwnerId(ownerId);
    }

    @Transactional
    public Pet changePetOwner(ChangePetOwnerDto dto){
        Owner oldOwner = ownerService.getOwnerById(dto.getOldOwnerId());
        Owner newOwner = ownerService.getOwnerById(dto.getNewOwnerId());
        Pet pet = findPetById(dto.getPetId());

        if(!isPetOwnerCorrect(pet, newOwner)){
            throw new IllegalArgumentException(oldOwner.getFirstName() + " " + oldOwner.getLastName() + ", "
                    + pet.getPetName() + " isimli kişi evcil hayvanın sahibi değil. Sadece kendi hayvanınızın sahibini değiştirebilirsiniz.");
        }
        if(oldOwner.equals(newOwner)){
            throw new IllegalArgumentException("Eski ve yeni sahipler farklı olmalıdır.");
        }

        pet.setOwner(newOwner);
        newOwner.setPetCount(newOwner.getPetCount() + 1);
        oldOwner.setPetCount(oldOwner.getPetCount() - 1);
        petRepo.save(pet);
        ownerRepo.save(oldOwner);
        ownerRepo.save(newOwner);
        return pet;
    }

    @Transactional
    public void deletePetById(PetDeleteDto dto){
        Pet pet = findPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        boolean correctOwner = isPetOwnerCorrect(pet, owner);
        if(!correctOwner){
            throw new WrongOwnerException();
        }
        owner.setPetCount(owner.getPetCount() - 1);
        petRepo.delete(pet);
    }

    public boolean isPetOwnerCorrect(Pet pet, Owner owner){
        if(pet.getOwner().equals(owner)){
            return true;
        }
        return false;
    }

}
