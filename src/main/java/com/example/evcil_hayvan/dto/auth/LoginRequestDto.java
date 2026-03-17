package com.example.evcil_hayvan.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
