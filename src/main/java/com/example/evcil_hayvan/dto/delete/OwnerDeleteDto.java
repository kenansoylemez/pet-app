package com.example.evcil_hayvan.dto.delete;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerDeleteDto {

    @NotNull
    private Long ownerId;

}
