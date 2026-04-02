package com.example.evcil_hayvan.service;



import com.example.evcil_hayvan.dto.create.CreatePetPhotoDto;
import com.example.evcil_hayvan.entity.PetPhoto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.repository.PetPhotoRepo;
import com.example.evcil_hayvan.exceptions.WrongOwnerException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PetPhotoService {

    private final PetPhotoRepo petPhotoRepo;
    private final PetService petService;

    public PetPhotoService(PetPhotoRepo petPhotoRepo, PetService petService) {
        this.petPhotoRepo = petPhotoRepo;
        this.petService = petService;
    }

    @Transactional
    public PetPhoto addPhoto(CreatePetPhotoDto dto) {
        Pet pet = petService.getPetById(dto.getPetId());
        String photoUrl = dto.getPhotoUrl();
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))) {
            throw new WrongOwnerException();
        }
        if (photoUrl == null || photoUrl.equals("") || photoUrl.trim().isEmpty()) {
            throw new EntityNotFoundException("Fotoğraf bulunamadı.");
        }
        PetPhoto petPhoto = new PetPhoto(LocalDateTime.now(), dto.getCaption(), photoUrl, pet);
        pet.getPetPhotos().add(petPhoto);
        
        return petPhotoRepo.save(petPhoto);
    }


    @Transactional
    public void deletePhoto(DeletePetPhotoDto dto){
        PetPhoto PetPhoto = petPhotoRepo.findById(dto.getPetPhotoId());
        orElseThrow() -> new EntityNotFoundException("Silinecek fotoğraf bulunamadı: ID = " + dto.getPetPhotoId());

        Pet pet = petPhoto.getPet();
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))) {
            throw new WrongOwnerException();
        }
        pet.getPetPhotos().remove(petPhoto);
        petPhotoRepo.delete(petPhoto);
     }

     @Transactional
    public PetPhoto updatePhotoCaption(UpdatePetPhotoCaptionDto dto) {
        
        PetPhoto petPhoto = petPhotoRepo.findPetPhotoByPetPhotoId(dto.getPetPhotoId())
                .orElseThrow(() -> new EntityNotFoundException("Güncellenecek fotoğraf bulunamadı: ID = " + dto.getPetPhotoId()));

        Pet pet = petPhoto.getPet();
        if(!(pet.getOwner().getOwnerId().equals(dto.getOwnerId()))) {
            throw new WrongOwnerException();
        }

        petPhoto.setPetPhotoCaption(dto.getNewCaption());

        return petPhotoRepo.save(petPhoto);
    }


    }
