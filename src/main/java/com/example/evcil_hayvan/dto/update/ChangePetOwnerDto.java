package com.example.evcil_hayvan.dto.update;

import lombok.Data;

@Data
public class ChangePetOwnerDto {

    private Long oldOwnerId;

    private Long newOwnerId;

    private Long petId;

}
