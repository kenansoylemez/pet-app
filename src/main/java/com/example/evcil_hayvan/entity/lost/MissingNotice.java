package com.example.evcil_hayvan.entity.lost;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "missing_notice")
public class MissingNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missing_notice_id")
    private Long missingNoticeId;

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_seen_location")
    @Size(min = 1, max = 150)
    private String lastSeenLocation;

    @Column(name = "last_seen_time")
    private LocalDateTime lastSeenTime;

    @Column(name = "description")
    private String description;

    @Column(name = "is_found")
    private boolean isFound;

    @Column(name = "found_time")
    private LocalDateTime foundTime;

    public MissingNotice() {

    }

    public MissingNotice(Long petId, String lastSeenLocation, LocalDateTime lastSeenTime, String description){
        this.petId = petId;
        this.createdTime = LocalDateTime.now();
        this.lastSeenLocation = lastSeenLocation;
        this.lastSeenTime = lastSeenTime;
        this.description = description;
        this.isFound = false;
    }
}
