package com.example.evcil_hayvan.repository.pets;

import com.example.evcil_hayvan.entity.pets.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepo extends JpaRepository<Cat, Long> {

    Optional<Cat> findCatByPetId(Long petId);
}
