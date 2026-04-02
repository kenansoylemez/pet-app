package com.example.evcil_hayvan.entity.pets;

import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.enums.DogBreed;
import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.enums.Species;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "dogs")
public class Dog extends Pet {

    @Enumerated(EnumType.STRING)
    @Column(name = "breed")
    private DogBreed dogBreed;

    public Dog(String petName, LocalDate petBirthDate, Gender gender, Owner owner, String petProfilePhotoUrl, Double weight, DogBreed dogBreed, Boolean isNeutered) {
        super(petName, petBirthDate, gender, owner, petProfilePhotoUrl, weight, isNeutered);
        setSpecies(Species.DOG);
        this.dogBreed = dogBreed;
    }

    public Dog(){}

}
