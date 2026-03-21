package com.example.evcil_hayvan.dto.update.pet;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import lombok.Data;

@Data
public class UpdatePetProfileDto extends OwnerPetBaseDto {

    private String newPetName;

    private Double newWeight;

    private String newProfilePhotoUrl;
}
