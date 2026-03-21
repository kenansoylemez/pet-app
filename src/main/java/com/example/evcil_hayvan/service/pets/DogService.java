package com.example.evcil_hayvan.service.pets;

import com.example.evcil_hayvan.dto.create.pet.CreateDogDtoCreate;
import com.example.evcil_hayvan.dto.update.pet.dog.UpdateDogBreedDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.pets.Dog;
import com.example.evcil_hayvan.enums.DogBreed;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.DogRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional
    public Dog addDog(CreateDogDtoCreate dto){

        Owner owner = ownerService.getOwnerById(dto.getOwnerId());

        Dog dog = new Dog(
                dto.getPetName(),
                dto.getPetBirthDate(),
                dto.getGender(),
                owner,
                dto.getPetProfilePhotoUrl(),
                dto.getWeight(),
                dto.getBreed()
        );

        double age = petService.calculatePetAge(dto.getPetBirthDate());
        owner.setPetCount(owner.getPetCount() + 1);
        ownerRepo.save(owner);
        return dogRepo.save(dog);
    }

    public Dog getDogById(Long dogId){
        Dog dog = dogRepo.findDogByPetId(dogId)
                .orElseThrow(() -> new EntityNotFoundException("Köpek bulunamadı: ID = " + dogId));
        return dog;
    }

    @Transactional
    public Dog updateDogBreed(UpdateDogBreedDto dto){
        Dog dog = getDogById(dto.getPetId());

        if(!(dog.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(dto.getNewDogBreed() == null){
            return dog;
        }

        if(dto.getNewDogBreed().equals(dog.getDogBreed())){
            return dog;
        }

        if(dog.getDogBreed().equals(DogBreed.OTHER) || dog.getDogBreed().equals(DogBreed.UNKNOWN)){
            if(dto.getNewDogBreed().equals(DogBreed.OTHER) || dto.getNewDogBreed().equals(DogBreed.UNKNOWN)){
                dog.setDogBreed(dto.getNewDogBreed());
            }else{
                dog.setDogBreed(dto.getNewDogBreed());
            }
        }else{
            if(dog.getBreedChangedAt() == null){
                if(!(dto.getNewDogBreed().equals(DogBreed.OTHER) || dto.getNewDogBreed().equals(DogBreed.UNKNOWN))){
                    dog.setDogBreed(dto.getNewDogBreed());
                    dog.setBreedChangedAt(LocalDateTime.now());
                }else{
                    throw new IllegalStateException("Köpeklerin ırk bilgisi bir kere girildikten sonra" +
                            "BİLİNMEYEN veya DİĞER değerine değiştirilemez.");
                }
            }else{
                throw new IllegalStateException("Köpeklerin ırkı sadece bir kere değiştirilebilir.");
            }
        }

        return dogRepo.save(dog);
    }

}
