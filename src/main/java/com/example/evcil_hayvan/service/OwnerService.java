package com.example.evcil_hayvan.service;

import com.example.evcil_hayvan.dto.delete.OwnerDeleteDto;
import com.example.evcil_hayvan.dto.create.OwnerRegisterationDto;
import com.example.evcil_hayvan.dto.update.OwnerUpdateEmailDto;
import com.example.evcil_hayvan.dto.update.OwnerUpdatePasswordDto;
import com.example.evcil_hayvan.dto.update.OwnerUpdateProfileInfoDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.exceptions.SamePasswordException;
import com.example.evcil_hayvan.exceptions.WrongPasswordException;
import com.example.evcil_hayvan.repository.OwnerRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepo ownerRepo;

    public OwnerService(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    public Owner addOwner(OwnerRegisterationDto dto){
        Owner owner = new Owner(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getPhoneNumber()
        );
        return ownerRepo.save(owner);
    }

    @Transactional
    public void deleteOwner(OwnerDeleteDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        ownerRepo.delete(owner);
    }

    @Transactional
    public Owner updateOwnerPassword(OwnerUpdatePasswordDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        if(!(dto.getOldPassword().equals(owner.getPassword()))){ //Eski şifre doğru mu? Kullanıcı kontrolü için
            throw new WrongPasswordException();
        }
        if(dto.getNewPassword().equals(owner.getPassword())){ //Yeni şifrenin eskisiyle aynı olmaması için
            throw new SamePasswordException();
        }
        owner.setPassword(dto.getNewPassword());
        return ownerRepo.save(owner);
    }

    public Owner getOwnerById(Long ownerId){
        Owner owner = ownerRepo.findOwnerByOwnerId(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: ID = " + ownerId));
        return owner;
    }

    public Owner updateOwnerProfileInfo(OwnerUpdateProfileInfoDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        if(dto.getFirstName() != null && !(owner.getFirstName().equals(dto.getFirstName()))){
            owner.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !(owner.getLastName().equals(dto.getLastName()))){
            owner.setLastName(dto.getLastName());
        }
        if(dto.getPhoneNumber() != null && !(owner.getPhoneNumber().equals(dto.getPhoneNumber()))){
            owner.setPhoneNumber(dto.getPhoneNumber());
        }
        return ownerRepo.save(owner);
    }

    public Owner updateOwnerEmail(OwnerUpdateEmailDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        if(dto.getNewEmail() != null && !(owner.getEmail().equals(dto.getNewEmail()))){
            owner.setEmail(dto.getNewEmail());
        }
        return ownerRepo.save(owner);
    }

    public Owner getOwnerByEmail(String email){
        Owner owner = ownerRepo.findOwnerByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı: ID = " + email));
        return owner;
    }

}
