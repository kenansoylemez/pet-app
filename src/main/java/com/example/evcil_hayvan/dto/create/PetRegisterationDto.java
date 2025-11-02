package com.example.evcil_hayvan.dto.create;

import com.example.evcil_hayvan.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PetRegisterationDto {

    private String petName;

    private LocalDate petBirthDate;

    private Gender gender;

    private Long ownerId;

}
