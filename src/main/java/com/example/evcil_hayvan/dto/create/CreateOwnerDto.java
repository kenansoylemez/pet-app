package com.example.evcil_hayvan.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOwnerDto {

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
    @Size(min = 8, message = "Şifre en az 8 karakter uzunluğunda olmalıdır.")
    private String password;

    @Size(min = 10, max = 10, message = "Lütfen telefon numaraınızı başında 0 olmayacak şekilde girin.")
    private String phoneNumber;

    @Size(min = 10, max = 500)
    private String profilePhotoUrl;
}
