package com.example.evcil_hayvan.entity.petHealth;

import com.example.evcil_hayvan.entity.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "HEALTH_vaccinations")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Long vaccinationId;

    @Column(name = "vaccination_name", nullable = false)
    private String vaccineName;

    @Column(name = "vaccination_date", nullable = false)
    @PastOrPresent
    private LocalDate vaccinationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public Vaccination(String vaccineName, LocalDate vaccinationDate, Pet pet) {
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
        this.pet = pet;
    }

    public Vaccination(){

    }
}
