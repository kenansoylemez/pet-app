package com.example.evcil_hayvan.entity.pets;

import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.enums.CatBreed;
import com.example.evcil_hayvan.enums.Gender;
import com.example.evcil_hayvan.enums.Species;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cats")
public class Cat extends Pet {

    @Enumerated(EnumType.STRING)
    @Column(name = "breed")
    private CatBreed catBreed;

    public Cat(String petName, LocalDate petBirthDate, Gender gender, Owner owner, CatBreed catBreed) {
        super(petName, petBirthDate, gender, owner);
        setSpecies(Species.CAT);
        this.catBreed = catBreed;
    }

    public Cat(){}
}
