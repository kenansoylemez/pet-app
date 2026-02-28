package com.example.evcil_hayvan.dto.update.pet;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePetBaseDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long petId;

}
