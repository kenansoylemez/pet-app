package com.example.evcil_hayvan.service;

import com.example.evcil_hayvan.dto.create.CreatePetPhotoDto;
import com.example.evcil_hayvan.dto.delete.DeletePetDto;
import com.example.evcil_hayvan.dto.get.GetPetAgeDto;
import com.example.evcil_hayvan.dto.update.pet.CorrectPetIdentityDto;
import com.example.evcil_hayvan.dto.update.pet.UpdatePetProfileDto;
import com.example.evcil_hayvan.dto.update.pet.UpdatePetOwnerDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.pets.PetPhoto;
import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.PetRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public double calculatePetAge(LocalDate petBirthDate) {
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
        if (plusMonths >= 6) {
            fraction = 0.5D;
        } else {
            fraction = 0D;
        }
        finalAge = plusYears + fraction;
        return finalAge;
    }

    @Transient
    public Double getPetAge(GetPetAgeDto dto) {
        return calculatePetAge(getPetById(dto.getPetId()).getPetBirthDate());
    }

    public Pet getPetById(Long petId) {
        return petRepo.findPetByPetId(petId)
                .orElseThrow(() -> new EntityNotFoundException("Evcil hayvan bulunamadı: ID = " + petId));
    }

    public List<Pet> findAllPetsByOwnerId(Long ownerId) {
        ownerRepo.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: ID = " + ownerId));
        return petRepo.findPetsByOwner_OwnerId(ownerId);
    }

    @Transactional
    public Pet changePetOwner(UpdatePetOwnerDto dto) {
        Owner oldOwner = ownerService.getOwnerById(dto.getOwnerId());
        Owner newOwner = ownerService.getOwnerById(dto.getNewOwnerId());
        Pet pet = getPetById(dto.getPetId());

        if (!(pet.getOwner().getOwnerId().equals(oldOwner.getOwnerId()))){
            throw new IllegalArgumentException(oldOwner.getFirstName() + " " + oldOwner.getLastName()
                     + pet.getPetName() + " isimli hayvanın sahibi değil. Sadece kendi hayvanınızın sahibini değiştirebilirsiniz.");
        }
        if (oldOwner.equals(newOwner)) {
            throw new IllegalArgumentException("Eski ve yeni sahipler farklı olmalıdır.");
        }

        pet.setOwner(newOwner);
        newOwner.setPetCount(newOwner.getPetCount() + 1);
        oldOwner.setPetCount(oldOwner.getPetCount() - 1);
        ownerRepo.save(oldOwner);
        ownerRepo.save(newOwner);
        return petRepo.save(pet);
    }

    @Transactional
    public void deletePetById(DeletePetDto dto) {
        Pet pet = getPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(pet.getOwner().getOwnerId().equals(owner.getOwnerId()))) {
            throw new WrongOwnerException();
        }
        owner.setPetCount(owner.getPetCount() - 1);
        petRepo.delete(pet);
    }

    @Transactional
    public Pet updatePetProfilePhoto(UpdatePetProfileDto dto) {
        Pet pet = getPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(pet.getOwner().getOwnerId().equals(owner.getOwnerId()))) {
            throw new WrongOwnerException();
        }
        String photoUrl = dto.getNewProfilePhotoUrl();
        if (photoUrl == null || photoUrl.equals("")) {
            throw new EntityNotFoundException("Fotoğraf bulunamadı.");
        } else if (pet.getPetProfilePhotoUrl().equals(photoUrl)) {
            return pet;
        } else {
            pet.setPetProfilePhotoUrl(photoUrl);
        }
        return petRepo.save(pet);
    }

    @Transactional
    public Pet addPhoto(CreatePetPhotoDto dto) {
        Pet pet = getPetById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        String photoUrl = dto.getPhotoUrl();
        if(!(pet.getOwner().getOwnerId().equals(owner.getOwnerId()))) {
            throw new WrongOwnerException();
        }
        if (photoUrl == null || photoUrl.equals("") || photoUrl.trim().isEmpty()) {
            throw new EntityNotFoundException("Fotoğraf bulunamadı.");
        } else {
            PetPhoto petPhoto = new PetPhoto(LocalDateTime.now(), dto.getCaption(), photoUrl, pet);
            pet.getPetPhotos().add(petPhoto);
        }
        return petRepo.save(pet);
    }

    @Transactional
    public Pet updatePetProfile(UpdatePetProfileDto dto) {
        Pet pet = getPetById(dto.getPetId());

        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(!(dto.getNewProfilePhotoUrl() == null || dto.getNewProfilePhotoUrl().equals(""))){
            pet.setPetProfilePhotoUrl(dto.getNewProfilePhotoUrl());
        }

        if(dto.getNewPetName() != null || !(dto.getNewPetName().trim().equals(""))){
            if(!(dto.getNewPetName().trim().equals(pet.getPetName().trim()))){
                pet.setPetName(dto.getNewPetName());
            }
        }

        if(dto.getNewWeight() != null || dto.getNewWeight() != pet.getWeight()){
            pet.setWeight(dto.getNewWeight());
        }

        return petRepo.save(pet);
    }

    @Transactional
    public Pet correctPetIdentity(CorrectPetIdentityDto dto) {
        Pet pet = getPetById(dto.getPetId());

        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(dto.getNewGender() != null){
            if(pet.getGender() == Gender.NOT_SPECIFIED){
                pet.setGender(dto.getNewGender());
            }else{
                throw new IllegalStateException("Cinsiyet bilgisi girildikten sonra değiştirilemez.");
            }
        }

        if(dto.getNewBirthDate() != null){
            LocalDate currentBirthDate = pet.getPetBirthDate();
            LocalDate newBirthDate = dto.getNewBirthDate();

            if(currentBirthDate == null){
                pet.setPetBirthDate(newBirthDate);
            }else if(newBirthDate.equals(currentBirthDate)){
                // same value, no change
            }else if(pet.getBirthDateChangedAt() == null){
                pet.setPetBirthDate(newBirthDate);
                pet.setBirthDateChangedAt(LocalDateTime.now());
            }else{
                throw new IllegalStateException("Doğum tarihi daha önce değiştirilmiş. " +
                        "Doğum tarihi değeri sadece bir kere değiştirilebilir.");
            }
        }

        return petRepo.save(pet);
    }
}