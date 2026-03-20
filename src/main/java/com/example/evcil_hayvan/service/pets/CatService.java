package com.example.evcil_hayvan.service.pets;

import com.example.evcil_hayvan.dto.create.pet.CreateCatDtoCreate;
import com.example.evcil_hayvan.dto.update.pet.cat.UpdateCatBreedDtoOwnerPet;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.pets.Cat;
import com.example.evcil_hayvan.enums.CatBreed;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.CatRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CatService {

    private final CatRepo catRepo;
    private final PetService petService;
    private final OwnerRepo ownerRepo;
    private final OwnerService ownerService;

    public CatService(CatRepo catRepo, PetService petService, OwnerRepo ownerRepo, OwnerService ownerService) {
        this.catRepo = catRepo;
        this.petService = petService;
        this.ownerRepo = ownerRepo;
        this.ownerService = ownerService;
    }

    @Transactional
    public Cat addCat(CreateCatDtoCreate dto){

        Owner owner = ownerService.getOwnerById(dto.getOwnerId());

        Cat cat = new Cat(
                dto.getPetName(),
                dto.getPetBirthDate(),
                dto.getGender(),
                owner,
                dto.getPetProfilePhotoUrl(),
                dto.getWeight(),
                dto.getBreed()
        );

        owner.setPetCount(owner.getPetCount() + 1);
        ownerRepo.save(owner);
        return catRepo.save(cat);
    }

    public Cat getCatById(Long catId){
        Cat cat = catRepo.findCatByPetId(catId)
                .orElseThrow(() -> new EntityNotFoundException("Kedi bulunamadı: ID = " + catId));
        return cat;
    }

    @Transactional
    public Cat updateCatBreed(UpdateCatBreedDtoOwnerPet dto){
        Cat cat = getCatById(dto.getPetId());

        if(!(cat.getOwner().getOwnerId().equals(dto.getOwnerId()))){
            throw new WrongOwnerException();
        }

        if(dto.getNewCatBreed() == null){
            return cat;
        }

        if(dto.getNewCatBreed().equals(cat.getCatBreed())){
            return cat;
        }

        if(cat.getCatBreed().equals(CatBreed.OTHER) || cat.getCatBreed().equals(CatBreed.UNKNOWN)){
            if(dto.getNewCatBreed().equals(CatBreed.OTHER) || dto.getNewCatBreed().equals(CatBreed.UNKNOWN)){
                cat.setCatBreed(dto.getNewCatBreed());
            }else{
                cat.setCatBreed(dto.getNewCatBreed());
            }
        }else{
            if(cat.getBreedChangedAt() == null){
                if(!(dto.getNewCatBreed().equals(CatBreed.OTHER) || dto.getNewCatBreed().equals(CatBreed.UNKNOWN))){
                    cat.setCatBreed(dto.getNewCatBreed());
                    cat.setBreedChangedAt(LocalDateTime.now());
                }else{
                    throw new IllegalStateException("Kedilerin ırk bilgisi bir kere girildikten sonra" +
                            "BİLİNMEYEN veya DİĞER değerine değiştirilemez.");
                }
            }else{
                throw new IllegalStateException("Kedilerin ırkı sadece bir kere değiştirilebilir.");
            }
        }

        return catRepo.save(cat);
    }
}
