package com.example.evcil_hayvan.dto.create.health;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateVetVisitDto {

    private String vetVisitCause;

    @PastOrPresent(message = "Sadece hayvanınızın gittiği kontrolleri ekleyebilirsiniz. Gelecek zamanlar kabul edilmemektedir.")
    private LocalDate vetVisitDate;

    private String vetVisitResult;

    private Long petId;

    private Long ownerId;

}
