package com.example.evcil_hayvan.dto.update;

import com.example.evcil_hayvan.entity.pets.Cat;
import com.example.evcil_hayvan.enums.CatBreed;
import lombok.Data;

@Data
public class CatUpdateDto extends BasePetUpdateDto{

    private CatBreed newCatBreed;
}
