package com.example.evcil_hayvan.controller;

import com.example.evcil_hayvan.dto.delete.DeletePetDto;
import com.example.evcil_hayvan.dto.update.pet.UpdatePetOwnerDto;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import com.example.evcil_hayvan.service.pets.CatService;
import com.example.evcil_hayvan.service.pets.DogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final CatService catService;
    private final PetService petService;
    private final DogService dogService;
    private final OwnerService ownerService;

    public PetController(CatService catService, PetService petService, DogService dogService, OwnerService ownerService) {
        this.catService = catService;
        this.petService = petService;
        this.dogService = dogService;
        this.ownerService = ownerService;
    }

    @GetMapping("/find/{petId}")
    public Pet getPetById(@PathVariable Long petId) {
        return petService.getPetById(petId);
    }

    @GetMapping("/my-pets/{ownerId}")
    public List<Pet> getAllPetsByOwner(@PathVariable Long ownerId){
        return petService.findAllPetsByOwnerId(ownerId);
    }

    @PutMapping("/change-owner")
    public Pet changePetOwner(@RequestBody UpdatePetOwnerDto dto){
        return petService.changePetOwner(dto);
    }

    @DeleteMapping("/delete")
    public void deletePetById(@RequestBody DeletePetDto dto){
        petService.deletePetById(dto);
    }



}
