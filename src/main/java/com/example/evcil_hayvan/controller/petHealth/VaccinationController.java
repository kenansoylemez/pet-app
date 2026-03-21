package com.example.evcil_hayvan.controller.petHealth;

import com.example.evcil_hayvan.dto.create.health.CreateVaccinationDto;
import com.example.evcil_hayvan.dto.delete.health.DeleteVaccinationDto;
import com.example.evcil_hayvan.dto.get.GetAllByPetDto;
import com.example.evcil_hayvan.dto.update.health.UpdateVaccinationDto;
import com.example.evcil_hayvan.entity.petHealth.Vaccination;
import com.example.evcil_hayvan.service.petHealth.VaccinationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }


    @GetMapping("/get-all")
    public List<Vaccination> getVaccinationsByPet(@RequestBody GetAllByPetDto dto){
        return vaccinationService.getAllVaccinationsByPetId(dto);
    }

    @GetMapping("/get-by-vaccination-id")
    public Vaccination getVaccinationById(@PathVariable Long vaccinationId){
        return vaccinationService.getVaccinationById(vaccinationId);
    }

    @PostMapping("/create")
    public Vaccination createVaccination(@RequestBody CreateVaccinationDto dto){
        return vaccinationService.createVaccination(dto);
    }

    @PutMapping("/update")
    public Vaccination updateVaccination(@RequestBody UpdateVaccinationDto dto){
        return vaccinationService.updateVaccination(dto);
    }

    @DeleteMapping("/delete")
    public void deleteVaccination(@RequestBody DeleteVaccinationDto dto){
        vaccinationService.deleteVaccination(dto);
    }

}
