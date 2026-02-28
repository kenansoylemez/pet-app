package com.example.evcil_hayvan.dto.update.owner;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateOwnerProfileInfoDto {

    private Long ownerId;

    private String firstName;

    private String lastName;

    @Size(min = 10, max = 10)
    private String phoneNumber;

}
