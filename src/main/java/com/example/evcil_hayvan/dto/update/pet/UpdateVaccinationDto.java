package com.example.evcil_hayvan.dto.update.pet;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVaccinationDto extends UpdatePetBaseDto{

    @NotNull
    private Long vaccinationId;

    private String newVaccineName;

    private LocalDate newVaccinationDate;
}
