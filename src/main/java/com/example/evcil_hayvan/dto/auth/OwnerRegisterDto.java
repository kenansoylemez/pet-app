package com.example.evcil_hayvan.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerRegisterDto {

    @NotNull
    @Size(min = 2, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Email(message = "Girdiğiniz değer, email formatına uygun değil.")
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(min = 2, max = 30, message = "Kullanıcı adı en az 2, en fazla 30 karakter uzunluğunda olmalıdır.")
    private String username;

    @NotNull
    @Size(min = 8, message = "Şifre en az 8 karakter uzunluğunda olmalıdır.")
    private String password;

    @Size(min = 10, max = 10, message = "Lütfen telefon numaraınızı başında 0 olmayacak şekilde girin.")
    private String phoneNumber;

    @Size(min = 10, max = 500)
    private String profilePhotoUrl;
}
