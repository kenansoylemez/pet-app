package com.example.evcil_hayvan.dto.create.health;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateVaccinationDto {

    private String vaccineName;

    @NotNull
    private Long petId;

    @PastOrPresent(message = "Sadece hayvanınızın olduğu aşıları girebilirsiniz. Gelecek tarih kabul edilmemektedir.")
    private LocalDate vaccinationDate;

    private Long ownerId;

}
