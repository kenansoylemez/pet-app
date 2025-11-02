package com.example.evcil_hayvan.dto.update;

import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVaccinationDto {

    @NotNull
    private Long vaccinationId;

    private String newVaccineName;

    private LocalDate newVaccinationDate;

    private Long ownerId;
}
