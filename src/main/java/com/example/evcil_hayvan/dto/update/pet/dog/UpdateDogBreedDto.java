package com.example.evcil_hayvan.dto.update.pet.dog;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import com.example.evcil_hayvan.enums.DogBreed;
import lombok.Data;

@Data
public class UpdateDogBreedDto extends OwnerPetBaseDto {

    private DogBreed newDogBreed;

}
