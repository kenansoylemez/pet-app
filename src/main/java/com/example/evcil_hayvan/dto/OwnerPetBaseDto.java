package com.example.evcil_hayvan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerPetBaseDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long petId;

}
