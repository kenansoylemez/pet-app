package com.example.evcil_hayvan.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerUpdatePasswordDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

}
