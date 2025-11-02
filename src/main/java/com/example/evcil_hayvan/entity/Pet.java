package com.example.evcil_hayvan.entity;


import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.enums.Species;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

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

    @Column(name = "pet_age")
    private Double petAge;

    @Column(name = "pet_birth_date")
    private LocalDate petBirthDate;

    @Column(name = "species")
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    public Pet() {}

    public Pet(String petName, LocalDate petBirthDate, Gender gender, Owner owner) {
        this.petName = petName;
        this.petBirthDate = petBirthDate;
        this.gender = gender;
        this.owner = owner;
    }

}
