package com.example.evcil_hayvan.entity;


import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "pet_name", nullable = false)
    @Size(max = 50)
    private String petName;

    @Column(name = "pet_birth_date")
    @Past(message = "Doğum tarihi geçmişte olmak zorundadır.")
    private LocalDate petBirthDate;

    @Column(name = "birth_date_changed_at")
    private LocalDateTime birthDateChangedAt;

    @Column(name = "species", updatable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(name = "gender", updatable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Cinsiyet bilgisi 'Belirsiz' olarak girilmediği sürece değiştirilemez. Doğru girdiğinize emin olun.")
    private Gender gender;

    @Column(name = "breed_changed_at")
    private LocalDateTime breedChangedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private Owner owner;

    @Column(name = "pet_profile_photo_url")
    private String petProfilePhotoUrl;

    @Column(name = "weight")
    private Double weight;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PetPhoto> petPhotos = new ArrayList<>();

    @Column(name = "lost_status")
    private Boolean lostStatus;

    @Column(name = "neutered")
    private Boolean isNeutered;

    public Pet() {}

    public Pet(String petName, LocalDate petBirthDate, Gender gender, Owner owner,
               String petProfilePhotoUrl, Double weight) {
        this.petName = petName;
        this.petBirthDate = petBirthDate;
        this.birthDateChangedAt = null;
        this.gender = gender;
        this.breedChangedAt = null;
        this.owner = owner;
        this.petProfilePhotoUrl = petProfilePhotoUrl;
        this.weight = weight;
        this.lostStatus = false;
        this.isNeutered = false;
    }

}
