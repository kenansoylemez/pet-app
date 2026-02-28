package com.example.evcil_hayvan.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePetPhotoDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long petId;

    private String caption;

    private String photoUrl;
}
