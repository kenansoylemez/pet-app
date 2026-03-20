package com.example.evcil_hayvan.dto.update.lost;

import com.example.evcil_hayvan.dto.MissingNoticeBaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateMissingNoticeDto extends MissingNoticeBaseDto {

    private LocalDateTime lastSeenTime;

    private String lastSeenLocation;

    private String description;

}
