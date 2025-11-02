package com.example.evcil_hayvan.dto.create;

import com.example.evcil_hayvan.enums.DogBreed;
import com.example.evcil_hayvan.enums.Species;
import lombok.Data;

@Data
public class DogRegisterationDto extends PetRegisterationDto {

    private Species species;

    private DogBreed breed;

}
