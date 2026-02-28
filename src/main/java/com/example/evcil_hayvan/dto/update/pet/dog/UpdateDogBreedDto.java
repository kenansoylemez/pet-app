package com.example.evcil_hayvan.dto.update.pet.dog;

import com.example.evcil_hayvan.dto.update.pet.UpdatePetBaseDto;
import com.example.evcil_hayvan.enums.DogBreed;
import lombok.Data;

@Data
public class UpdateDogBreedDto extends UpdatePetBaseDto {

    private DogBreed newDogBreed;

}
