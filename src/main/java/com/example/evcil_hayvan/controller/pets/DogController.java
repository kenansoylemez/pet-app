package com.example.evcil_hayvan.controller.pets;

import com.example.evcil_hayvan.dto.create.DogRegisterationDto;
import com.example.evcil_hayvan.entity.pets.Dog;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import com.example.evcil_hayvan.service.pets.DogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogService dogService;
    private final PetService petService;
    private final OwnerService ownerService;

    public DogController(DogService dogService, PetService petService, OwnerService ownerService) {
        this.dogService = dogService;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @PostMapping("/add")
    public Dog addDog(@RequestBody DogRegisterationDto dto){
        return dogService.addDog(dto);
    }

}
