package com.example.evcil_hayvan.dto.update.owner;

import lombok.Data;

@Data
public class UpdateOwnerEmailDto {

    private String newEmail;

    private Long ownerId;

}
