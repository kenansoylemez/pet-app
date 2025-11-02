package com.example.evcil_hayvan.dto.create;

import com.example.evcil_hayvan.enums.CatBreed;
import com.example.evcil_hayvan.enums.Species;
import lombok.Data;

@Data
public class CatRegisterationDto extends PetRegisterationDto {

    private Species species;

    private CatBreed breed;
}
