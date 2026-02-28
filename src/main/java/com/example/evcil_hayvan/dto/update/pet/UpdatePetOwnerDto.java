package com.example.evcil_hayvan.dto.update.pet;

import lombok.Data;

@Data
public class UpdatePetOwnerDto extends UpdatePetBaseDto {

    private Long newOwnerId;

}
