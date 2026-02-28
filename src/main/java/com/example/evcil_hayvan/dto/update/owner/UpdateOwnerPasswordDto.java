package com.example.evcil_hayvan.dto.update.owner;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOwnerPasswordDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

}
