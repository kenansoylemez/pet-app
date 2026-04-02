package com.example.evcil_hayvan.repository;

import com.example.evcil_hayvan.entity.PetPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetPhotoRepo extends JpaRepository<PetPhoto, Long> {
    
    List<PetPhoto> findPetPhotosByPet_PetId(Long petId);

    Optional<PetPhoto> findPetPhotoByPetPhotoId(Long petPhotoId);
}