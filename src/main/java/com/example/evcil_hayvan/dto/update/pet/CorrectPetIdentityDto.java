package com.example.evcil_hayvan.dto.update.pet;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import com.example.evcil_hayvan.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CorrectPetIdentityDto extends OwnerPetBaseDto {

    private LocalDate newBirthDate;

    private Gender newGender;

}
