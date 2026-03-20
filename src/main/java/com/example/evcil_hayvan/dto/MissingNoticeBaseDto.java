package com.example.evcil_hayvan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissingNoticeBaseDto {

    @NotNull
    private Long missingNoticeId;

    private Long ownerId;


}
