package com.example.evcil_hayvan.service;

import com.example.evcil_hayvan.dto.delete.DeleteOwnerDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerProfilePhotoDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerEmailDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerPasswordDto;
import com.example.evcil_hayvan.dto.update.owner.UpdateOwnerProfileInfoDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.exceptions.*;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.security.validation.PasswordValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepo ownerRepo;
    private final PasswordValidator passwordValidator;
    public OwnerService(OwnerRepo ownerRepo, PasswordValidator passwordValidator) {
        this.ownerRepo = ownerRepo;
        this.passwordValidator = passwordValidator;
    }

    public Boolean isUsernameUnique(String username){
        if(ownerRepo.findOwnerByUsername(username).isPresent()){
            return false;
        }else{
            return true;
        }
    }

    public Boolean isEmailUnique(String email){
        if(ownerRepo.findOwnerByEmail(email).isPresent()){
            return false;
        }else{
            return true;
        }
    }

    public Boolean isPhoneNumberUnique(String phoneNumber){
        if(ownerRepo.findOwnerByPhoneNumber(phoneNumber).isPresent()){
            return false;
        }else{
            return true;
        }
    }

    public Owner getOwnerByUsername(String username){
        return ownerRepo.findOwnerByUsername(username)
                .orElseThrow(() -> new NoUserException());
    }

    @Transactional
    public void deleteOwner(DeleteOwnerDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        ownerRepo.delete(owner);
    }

    @Transactional
    public Owner updateOwnerPassword(UpdateOwnerPasswordDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        if(!(dto.getOldPassword().equals(owner.getPassword()))){ //Eski şifre doğru mu? Kullanıcı kontrolü için
            throw new WrongPasswordException();
        }
        if(!passwordValidator.isValid(dto.getNewPassword())){
            throw new InvalidPasswordException();
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

    @Transactional
    public Owner updateOwnerProfileInfo(UpdateOwnerProfileInfoDto dto){
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

    @Transactional
    public Owner updateOwnerEmail(UpdateOwnerEmailDto dto){
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

    @Transactional
    public Owner updateOwnerProfilePhoto(UpdateOwnerProfilePhotoDto dto){
        Owner owner = getOwnerById(dto.getOwnerId());
        String photoUrl = dto.getPhotoUrl();
        if(photoUrl == null || photoUrl.equals("")){
            throw new EntityNotFoundException("Fotoğraf bulunamadı.");
        }else if(owner.getOwnerProfilePhotoUrl().equals(photoUrl)){
            return owner;
        }else{
            owner.setOwnerProfilePhotoUrl(photoUrl);
        }
        return ownerRepo.save(owner);
    }


}
