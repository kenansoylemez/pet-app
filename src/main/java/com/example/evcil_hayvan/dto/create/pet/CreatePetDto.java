package com.example.evcil_hayvan.dto.create.pet;

import com.example.evcil_hayvan.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePetDto {

    private String petName;

    private LocalDate petBirthDate;

    private Gender gender;

    private Long ownerId;

    private String petProfilePhotoUrl;

    private Double weight;

}
