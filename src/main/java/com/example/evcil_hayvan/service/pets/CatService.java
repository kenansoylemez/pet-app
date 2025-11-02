package com.example.evcil_hayvan.service.pets;

import com.example.evcil_hayvan.dto.create.CatRegisterationDto;
import com.example.evcil_hayvan.dto.update.CatUpdateDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.pets.Cat;
import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.repository.pets.CatRepo;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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

    public Cat addCat(CatRegisterationDto dto){

        Owner owner = ownerService.getOwnerById(dto.getOwnerId());

        Cat cat = new Cat(
                dto.getPetName(),
                dto.getPetBirthDate(),
                dto.getGender(),
                owner,
                dto.getBreed()
        );

        double age = petService.calculatePetAge(dto.getPetBirthDate());
        cat.setPetAge(age);
        owner.setPetCount(owner.getPetCount() + 1);
        ownerRepo.save(owner);
        return catRepo.save(cat);
    }

    public Cat getCatById(Long catId){
        Cat cat = catRepo.findCatByPetId(catId)
                .orElseThrow(() -> new EntityNotFoundException("Kedi bulunamadı: ID = " + catId));
        return cat;
    }

    public Cat updateCat(CatUpdateDto dto){
        Cat cat = getCatById(dto.getPetId());
        Owner owner = ownerService.getOwnerById(dto.getOwnerId());
        if(!(petService.isPetOwnerCorrect(cat, owner))){
            throw new WrongOwnerException();
        }
        if(dto.getNewPetName() != null && !(cat.getPetName().equals(dto.getNewPetName()))){
            cat.setPetName(dto.getNewPetName());
        }
        if(dto.getNewPetBirthDate() != null && !(cat.getPetBirthDate().equals(dto.getNewPetBirthDate()))){
            cat.setPetBirthDate(dto.getNewPetBirthDate());
            double age = petService.calculatePetAge(dto.getNewPetBirthDate());
            cat.setPetAge(age);
        }
        if(dto.getNewPetGender() != null && !(cat.getGender().equals(dto.getNewPetGender()))){
            //hayvanların cinsiyeti sadece önceden NOT_SPECIFIED olarak tanımlandığı takdirde değiştirilebilir
            if(cat.getGender().equals(Gender.NOT_SPECIFIED)){
                cat.setGender(dto.getNewPetGender());
            }
        }
        return catRepo.save(cat);
    }
}
