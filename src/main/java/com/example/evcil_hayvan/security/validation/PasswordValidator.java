package com.example.evcil_hayvan.security.validation;

import com.example.evcil_hayvan.exceptions.InvalidPasswordException;
import com.example.evcil_hayvan.security.generator.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Component
public class PasswordValidator {

    public boolean isValid(String password){
        if(password.length()<8){
            return false;
        }
        boolean hasSpecial = containsSpecial(password);
        boolean hasNumber = containsNumber(password);
        boolean hasUppercase = containsUppercase(password);
        boolean hasLowercase = containsLowercase(password);
        boolean check = hasNumber && hasSpecial && hasUppercase && hasLowercase;
        return check;
    }

    public String validatePassword(String password){
        if(isValid(password)){
            return password;
        }else{
            throw new InvalidPasswordException();
        }
    }

    public boolean containsSpecial(String password){
        for (char ch : password.toCharArray()) {
            if((ch >= 33 && ch <= 47) || (ch >= 58 && ch <= 64)
                    || (ch >= 91 && ch <= 95) || (ch >= 123 && ch <= 126)){
                return true;
            }
        }
        return false;
    }
    public boolean containsUppercase(String password){
        for(char ch : password.toCharArray()) {
            if((ch >= 65 && ch <= 90)){
                return true;
            }
        }
        return false;
    }

    public boolean containsLowercase(String password){
        for(char ch : password.toCharArray()) {
            if((ch >= 97 && ch <= 122)){
                return true;
            }
        }
        return false;
    }

    public boolean containsNumber(String password){
        for(char ch : password.toCharArray()) {
            if((ch >= 48 && ch <= 57)){
                return true;
            }
        }
        return false;
    }

}
