package com.example.evcil_hayvan.dto.update.pet.cat;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import com.example.evcil_hayvan.enums.CatBreed;
import lombok.Data;

@Data
public class UpdateCatBreedDto extends OwnerPetBaseDto {

    private CatBreed newCatBreed;
}
