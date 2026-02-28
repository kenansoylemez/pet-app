package com.example.evcil_hayvan.dto.update.pet;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVetVisitDto extends UpdatePetBaseDto{

    private Long vetVisitId;

    @PastOrPresent
    private LocalDate newVetVisitDate;

    private String newVetVisitCause;

    private String newVetVisitResult;
}
