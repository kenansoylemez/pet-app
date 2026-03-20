package com.example.evcil_hayvan.controller.pets;

import com.example.evcil_hayvan.dto.create.pet.CreateCatDtoCreate;
import com.example.evcil_hayvan.entity.pets.Cat;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import com.example.evcil_hayvan.service.pets.CatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatController {

    private final PetService petService;
    private final CatService catService;
    private final OwnerService ownerService;

    public CatController(PetService petService, CatService catService, OwnerService ownerService) {
        this.petService = petService;
        this.catService = catService;
        this.ownerService = ownerService;
    }

    @PostMapping("/add")
    public Cat addCat(@RequestBody CreateCatDtoCreate dto){
        return catService.addCat(dto);
    }

}
