package com.example.evcil_hayvan.security.service;

import com.example.evcil_hayvan.dto.auth.AuthLoginResponseDto;
import com.example.evcil_hayvan.dto.auth.LoginRequestDto;
import com.example.evcil_hayvan.dto.auth.OwnerRegisterDto;
import com.example.evcil_hayvan.entity.Owner;
import com.example.evcil_hayvan.exceptions.*;
import com.example.evcil_hayvan.repository.OwnerRepo;
import com.example.evcil_hayvan.security.validation.PasswordValidator;
import com.example.evcil_hayvan.service.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final OwnerRepo ownerRepo;
    private final PasswordValidator passwordValidator;
    private final PasswordEncoder passwordEncoder;
    private final OwnerService ownerService;

    public AuthService(OwnerRepo ownerRepo, PasswordValidator passwordValidator, PasswordEncoder passwordEncoder, OwnerService ownerService) {
        this.ownerRepo = ownerRepo;
        this.passwordValidator = passwordValidator;
        this.passwordEncoder = passwordEncoder;
        this.ownerService = ownerService;
    }

    @Transactional
    public Owner register(OwnerRegisterDto dto){
        if(!passwordValidator.isValid(dto.getPassword())){
            throw new InvalidPasswordException();
        }

        if(!(ownerService.isEmailUnique(dto.getEmail()))){
            throw new EmailExistsException();
        }

        if(!(ownerService.isUsernameUnique(dto.getUsername()))){
            throw new UsernameExistsException();
        }

        if(dto.getPhoneNumber() != null && !(ownerService.isPhoneNumberUnique(dto.getPhoneNumber()))){
            throw new PhoneNumberExistsException();
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        Owner owner = new Owner(
                dto.getFirstName().trim(),
                dto.getLastName().trim(),
                dto.getEmail().trim().toLowerCase(),
                dto.getUsername().trim().toLowerCase(),
                hashedPassword,
                dto.getPhoneNumber(),
                dto.getProfilePhotoUrl()
        );
        return ownerRepo.save(owner);
    }

    public AuthLoginResponseDto login(LoginRequestDto dto {
        if (ownerService.isUsernameUnique(dto.getUsername())) {
            throw new NoUserException();
        }

        Owner owner = ownerService.getOwnerByUsername(dto.getUsername());
        if (!(owner.getPassword().matches(dto.getPassword()))) {
            throw new WrongPasswordException();
        }

        // burası düzeltilecek

    }




    }

}
