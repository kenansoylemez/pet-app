package com.example.evcil_hayvan.dto.update.pet;

import lombok.Data;

@Data
public class UpdatePetProfileDto extends UpdatePetBaseDto {

    private String newPetName;

    private Double newWeight;

    private String newProfilePhotoUrl;
}
