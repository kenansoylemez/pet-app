package com.example.evcil_hayvan.dto.update.owner;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOwnerProfilePhotoDto {

    @NotNull
    private Long ownerId;

    private String photoUrl;

}
