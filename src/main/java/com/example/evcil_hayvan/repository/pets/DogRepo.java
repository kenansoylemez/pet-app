package com.example.evcil_hayvan.repository.pets;

import com.example.evcil_hayvan.entity.pets.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogRepo extends JpaRepository<Dog, Long> {

    Optional<Dog> findDogByPetId(Long petId);

}
