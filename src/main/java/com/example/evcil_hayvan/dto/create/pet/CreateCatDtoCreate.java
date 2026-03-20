package com.example.evcil_hayvan.dto.create.pet;

import com.example.evcil_hayvan.enums.CatBreed;
import com.example.evcil_hayvan.enums.Species;
import lombok.Data;

@Data
public class CreateCatDtoCreate extends CreatePetDto {

    private Species species;

    private CatBreed breed;
}
