package com.example.evcil_hayvan.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerUpdateProfileInfoDto {

    private Long ownerId;

    private String firstName;

    private String lastName;

    @Size(min = 10, max = 10)
    private String phoneNumber;

}
