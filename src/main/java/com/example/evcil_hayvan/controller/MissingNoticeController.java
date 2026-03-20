package com.example.evcil_hayvan.controller;

import com.example.evcil_hayvan.dto.create.lost.CreateMissingNoticeDto;
import com.example.evcil_hayvan.dto.get.GetMissingNoticeDto;
import com.example.evcil_hayvan.dto.update.lost.UpdateMissingNoticeDto;
import com.example.evcil_hayvan.entity.lost.MissingNotice;
import com.example.evcil_hayvan.service.lost.MissingNoticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missing-notice")
public class MissingNoticeController {

    private final MissingNoticeService missingNoticeService;

    public MissingNoticeController(MissingNoticeService missingNoticeService) {
        this.missingNoticeService = missingNoticeService;
    }

    @PostMapping("/create")
    public MissingNotice createMissingNotice(@RequestBody CreateMissingNoticeDto dto){
        return missingNoticeService.createMissingNotice(dto);
    }

    @PutMapping("/update")
    public MissingNotice updateMissingNotice(@RequestBody UpdateMissingNoticeDto dto){
        return missingNoticeService.updateMissingNotice(dto);
    }

    @PutMapping("/found")
    public MissingNotice foundMissingNotice(@RequestBody GetMissingNoticeDto dto){
        return missingNoticeService.foundMissingNotice(dto);
    }

    @DeleteMapping("/delete")
    public void deleteMissingNotice(@RequestBody GetMissingNoticeDto dto){
        missingNoticeService.deleteMissingNotice(dto);
    }

    @GetMapping("get-by-owner-id")
    public List<MissingNotice> getMissingNoticesByOwner(@PathVariable Long ownerId){
        return missingNoticeService.getMissingNoticesByOwnerId(ownerId);
    }

    @GetMapping("get-by-pet-id")
    public List<MissingNotice> getMissingNoticesByPet(@PathVariable Long petId){
        return missingNoticeService.getMissingNoticesByPetId(petId);
    }

}
