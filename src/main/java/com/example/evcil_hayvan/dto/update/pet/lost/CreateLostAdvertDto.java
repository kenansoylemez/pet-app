package com.example.evcil_hayvan.dto.update.pet.lost;

import com.example.evcil_hayvan.dto.update.pet.UpdatePetBaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateLostAdvertDto extends UpdatePetBaseDto {

    @Size(min = 1, max = 150, message = "Konum bilgisi en fazla 150 karakter olabilir.")
    private String lastSeenLocation;

    private LocalDateTime lastSeenTime;

    @Size(min = 1, max = 250, message = "Tarif bilgisi en fazla 250 karakter olabilir.")
    private String description;

}
