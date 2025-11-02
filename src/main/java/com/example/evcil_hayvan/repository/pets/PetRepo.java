package com.example.evcil_hayvan.repository.pets;

import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepo extends JpaRepository<Pet, Long> {

    List<Pet> findPetsByOwner_OwnerId(Long ownerId);
    Optional<Pet> findPetByPetId(Long petId);
}
