package com.example.evcil_hayvan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pet_photos")
public class PetPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long petPhotoId;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name = "caption")
    private String petPhotoCaption;

    @Column(name = "photo_url", length = 500)
    private String petPhotoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public PetPhoto(LocalDateTime uploadDate, String petPhotoCaption, String petPhotoUrl, Pet pet){
        this.uploadDate = LocalDateTime.now();
        this.petPhotoCaption = petPhotoCaption;
        this.petPhotoUrl = petPhotoUrl;
        this.pet = pet;
    }

    public PetPhoto(){
    }


}
