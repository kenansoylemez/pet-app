package com.example.evcil_hayvan.dto.update.pet;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import lombok.Data;

@Data
public class ChangeOwnerDto extends OwnerPetBaseDto {

    private Long newOwnerId;

}
