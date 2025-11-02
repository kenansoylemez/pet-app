package com.example.evcil_hayvan.entity.petHealth;

import com.example.evcil_hayvan.entity.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "HEALTH_vet_visits")
public class VetVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_visit_id")
    private Long vetVisitId;

    @Column(name = "vet_visit_cause", nullable = false)
    private String vetVisitCause;

    @Column(name = "vet_visit_date", nullable = false)
    @PastOrPresent
    private LocalDate vetVisitDate;

    @Column(name = "vet_visit_result", nullable = true)
    private String vetVisitResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public VetVisit(String vetVisitCause, LocalDate vetVisitDate, Pet pet, String vetVisitResult) {
        this.vetVisitCause = vetVisitCause;
        this.vetVisitDate = vetVisitDate;
        this.pet = pet;
        this.vetVisitResult = vetVisitResult;
    }

    public VetVisit(){}

}
