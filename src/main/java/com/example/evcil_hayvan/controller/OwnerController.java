package com.example.evcil_hayvan.controller;

import com.example.evcil_hayvan.dto.create.CreateOwnerDto;
import com.example.evcil_hayvan.dto.delete.DeleteOwnerDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerEmailDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerPasswordDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerProfileInfoDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import com.example.evcil_hayvan.service.pets.CatService;
import com.example.evcil_hayvan.service.pets.DogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final CatService catService;
    private final DogService dogService;

    public OwnerController(OwnerService ownerService, PetService petService, CatService catService, DogService dogService){
        this.ownerService = ownerService;
        this.petService = petService;
        this.catService = catService;
        this.dogService = dogService;
    }

    @PutMapping("/update-email")
    public Owner updateOwnerEmail(@RequestBody UpdateOwnerEmailDto dto){
        return ownerService.updateOwnerEmail(dto);
    }

    @PutMapping("/update-password")
    public Owner updateOwnerPassword(@RequestBody UpdateOwnerPasswordDto dto){
        return ownerService.updateOwnerPassword(dto);
    }

    @PutMapping("/update-profile-info")
    public Owner updateOwnerProfileInfo(@RequestBody UpdateOwnerProfileInfoDto dto){
        return ownerService.updateOwnerProfileInfo(dto);
    }

    @PostMapping("/register")
    public Owner registerOwner(@RequestBody CreateOwnerDto dto){
        return ownerService.addOwner(dto);
    }

    @DeleteMapping("/delete")
    public void deleteOwner(@RequestBody DeleteOwnerDto dto){
        ownerService.deleteOwner(dto);
    }

}
