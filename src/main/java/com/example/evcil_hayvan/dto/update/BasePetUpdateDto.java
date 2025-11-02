package com.example.evcil_hayvan.dto.update;

import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.enums.Species;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BasePetUpdateDto {

    private Long petId;

    private Long ownerId;

    private String newPetName;

    private LocalDate newPetBirthDate;

    private Gender newPetGender;
}
