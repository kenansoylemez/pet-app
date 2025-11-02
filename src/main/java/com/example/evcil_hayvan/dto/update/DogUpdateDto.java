package com.example.evcil_hayvan.dto.update;

import com.example.evcil_hayvan.enums.DogBreed;
import lombok.Data;

@Data
public class DogUpdateDto extends BasePetUpdateDto{

    private DogBreed newDogBreed;

}
