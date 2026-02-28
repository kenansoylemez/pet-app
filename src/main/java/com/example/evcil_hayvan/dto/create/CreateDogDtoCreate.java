package com.example.evcil_hayvan.dto.create;

import com.example.evcil_hayvan.enums.DogBreed;
import com.example.evcil_hayvan.enums.Species;
import lombok.Data;

@Data
public class CreateDogDtoCreate extends CreatePetDto {

    private Species species;

    private DogBreed breed;

}
