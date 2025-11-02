package com.example.evcil_hayvan.dto.update;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVetVisitDto {

    private Long vetVisitId;

    @PastOrPresent
    private LocalDate newVetVisitDate;

    private String newVetVisitCause;

    private String newVetVisitResult;

    private Long ownerId;
}
