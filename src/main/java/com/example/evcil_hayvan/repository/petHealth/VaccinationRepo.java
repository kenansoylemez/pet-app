package com.example.evcil_hayvan.repository.petHealth;

import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VaccinationRepo extends JpaRepository<Vaccination,Long> {
    Optional<Vaccination> findByVaccinationId(Long vaccinationId);
    List<Vaccination> findAllByPetPetId(Long petId);
    Optional<Vaccination> findByVaccinationDateAndPetPetId(LocalDate vaccinationDate, Long petId);
}
