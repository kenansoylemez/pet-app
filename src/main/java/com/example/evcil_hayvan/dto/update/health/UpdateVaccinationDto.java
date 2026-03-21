package com.example.evcil_hayvan.dto.update.health;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVaccinationDto extends OwnerPetBaseDto {

    @NotNull
    private Long vaccinationId;

    private String newVaccineName;

    private LocalDate newVaccinationDate;
}
