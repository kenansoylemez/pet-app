package com.example.evcil_hayvan.service.pets;

import com.example.evcil_hayvan.dto.create.DogRegisterationDto;
import com.example.evcil_hayvan.dto.update.DogUpdateDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.pets.Dog;
import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.DogRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private final DogRepo dogRepo;
    private final PetService petService;
    private final OwnerRepo ownerRepo;
    private final OwnerService ownerService;

    public DogService(DogRepo dogRepo, PetService petService, OwnerRepo ownerRepo, OwnerService ownerService) {
        this.dogRepo = dogRepo;
        this.petService = petService;
        this.ownerRepo = ownerRepo;
        this.ownerService = ownerService;
    }

    public Dog addDog(DogRegisterationDto dto){

        Owner owner = ownerService.getOwnerById(dto.getOwnerId());

        Dog dog = new Dog(
                dto.getPetName(),
                dto.getPetBirthDate(),
                dto.getGender(),
                owner,
                dto.getBreed()
        );

        double age = petService.calculatePetAge(dto.getPetBirthDate());
        dog.setPetAge(age);
        owner.setPetCount(owner.getPetCount() + 1);
        ownerRepo.save(owner);
        return dogRepo.save(dog);
    }

    public Dog getDogById(Long dogId){
        Dog dog = dogRepo.findDogByPetId(dogId)
                .orElseThrow(() -> new EntityNotFoundException("Köpek bulunamadı: ID = " + dogId));
        return dog;
    }

    public Dog updateDot(DogUpdateDto dto){
        Dog dog = getDogById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(petService.isPetOwnerCorrect(dog, owner))){
            throw new WrongOwnerException();
        }
        if(dto.getNewPetName() != null && !(dog.getPetName().equals(dto.getNewPetName()))){
            dog.setPetName(dto.getNewPetName());
        }
        if(dto.getNewPetBirthDate() != null && !(dog.getPetBirthDate().equals(dto.getNewPetBirthDate()))){
            dog.setPetBirthDate(dto.getNewPetBirthDate());
            double age = petService.calculatePetAge(dto.getNewPetBirthDate());
            dog.setPetAge(age);
        }
        if(dto.getNewPetGender() != null && !(dog.getGender().equals(dto.getNewPetGender()))){
            //hayvanların cinsiyeti sadece önceden NOT_SPECIFIED olarak tanımlandığı takdirde değiştirilebilir
            if(dog.getGender().equals(Gender.NOT_SPECIFIED)){
                dog.setGender(dto.getNewPetGender());
            }
        }
        return dogRepo.save(dog);
    }

}
