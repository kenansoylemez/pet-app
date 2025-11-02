package com.example.evcil_hayvan.repository;

import com.example.evcil_hayvan.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

    Optional<Owner> findOwnerByOwnerId(Long ownerId);
    Optional<Owner> findOwnerByEmail(String email);
    Optional<Owner> findOwnerByFirstNameAndLastName(String firstName, String lastName);
}
