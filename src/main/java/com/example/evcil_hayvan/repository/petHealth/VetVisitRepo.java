package com.example.evcil_hayvan.repository.petHealth;

import com.example.evcil_hayvan.entity.petHealth.VetVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VetVisitRepo extends JpaRepository<VetVisit, Long> {

    Optional<VetVisit> findByVetVisitId(Long vetVisitId);
    List<VetVisit> findAllByPetPetId(Long petId);
    Optional<VetVisit> findByVetVisitDateAndPetPetId(LocalDate vetVisitDate, Long petId);
}