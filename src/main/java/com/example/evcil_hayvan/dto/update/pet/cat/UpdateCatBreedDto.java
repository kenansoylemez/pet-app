package com.example.evcil_hayvan.dto.update.pet.cat;

import com.example.evcil_hayvan.dto.update.pet.UpdatePetBaseDto;
import com.example.evcil_hayvan.enums.CatBreed;
import lombok.Data;

@Data
public class UpdateCatBreedDto extends UpdatePetBaseDto {

    private CatBreed newCatBreed;
}
