package com.example.evcil_hayvan.dto.create.lost;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMissingNoticeDto extends OwnerPetBaseDto {

    private String lastSeenLocation;

    private LocalDateTime lastSeenTime;

    private String description;


}
