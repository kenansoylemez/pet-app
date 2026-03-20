package com.example.evcil_hayvan.dto.update.health;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVetVisitDtoOwnerPet extends OwnerPetBaseDto {

    private Long vetVisitId;

    @PastOrPresent
    private LocalDate newVetVisitDate;

    private String newVetVisitCause;

    private String newVetVisitResult;
}
