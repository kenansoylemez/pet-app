package com.example.evcil_hayvan.controller.petHealth;

import com.example.evcil_hayvan.dto.create.CreateVaccinationDto;
import com.example.evcil_hayvan.dto.create.CreateVetVisitDto;
import com.example.evcil_hayvan.dto.delete.DeleteVaccinationDto;
import com.example.evcil_hayvan.dto.delete.DeleteVetVisitDto;
import com.example.evcil_hayvan.dto.get.GetAllByPetDto;
import com.example.evcil_hayvan.dto.update.pet.UpdateVaccinationDto;
import com.example.evcil_hayvan.dto.update.pet.UpdateVetVisitDto;
import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import com.example.evcil_hayvan.entity.petHealth.VetVisit;
import com.example.evcil_hayvan.service.OwnerService;
import com.example.evcil_hayvan.service.PetService;
import com.example.evcil_hayvan.service.petHealth.VaccinationService;
import com.example.evcil_hayvan.service.petHealth.VetVisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
public class PetHealthController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final VetVisitService vetVisitService;
    private final VaccinationService vaccinationService;

    public PetHealthController(PetService petService,OwnerService ownerService,
                               VetVisitService vetVisitService, VaccinationService vaccinationService){
        this.petService = petService;
        this.ownerService = ownerService;
        this.vetVisitService = vetVisitService;
        this.vaccinationService = vaccinationService;
    }

    @PostMapping("/create-vet-visit")
    public VetVisit createVetVisit(@RequestBody CreateVetVisitDto dto){
        return vetVisitService.createVetVisit(dto);
    }

    @PostMapping("/create-vaccination")
    public Vaccination createVaccination(@RequestBody CreateVaccinationDto dto){
        return vaccinationService.createVaccination(dto);
    }

    @PutMapping("/update-vet-visit")
    public VetVisit updateVetVisit(@RequestBody UpdateVetVisitDto dto){
        return vetVisitService.updateVetVisit(dto);
    }

    @PutMapping("/update-vaccination")
    public Vaccination updateVaccination(@RequestBody UpdateVaccinationDto dto){
        return vaccinationService.updateVaccination(dto);
    }

    @DeleteMapping("/delete-vet-visit")
    public void deleteVetVisit(@RequestBody DeleteVetVisitDto dto){
        vetVisitService.deleteVetVisit(dto);
    }

    @DeleteMapping("/delete-vaccination")
    public void deleteVaccination(@RequestBody DeleteVaccinationDto dto){
        vaccinationService.deleteVaccination(dto);
    }

    @GetMapping("/get-all-vaccinations")
    public List<Vaccination> getAllVaccinations(@RequestBody GetAllByPetDto dto){
        return vaccinationService.getAllVaccinationsByPetId(dto);
    }

    @GetMapping("/get-all-vet-visits")
    public List<VetVisit> getAllVetVisits(@RequestBody GetAllByPetDto dto){
        return vetVisitService.getAllVetVisitsByPetId(dto);
    }

}
