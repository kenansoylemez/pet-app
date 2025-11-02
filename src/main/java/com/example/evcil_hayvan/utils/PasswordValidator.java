package com.example.evcil_hayvan.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Component
public class PasswordValidator {

    public boolean checkPassword(String password){
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

    public String autoCreatePassword(){
        Random random = new Random();
        ArrayList<Integer> asciiValues = new ArrayList<>();
        String generatedPassword = "";
        int passwordSize = random.nextInt(6) + 10;
        ArrayList<Character> passwordChars = new ArrayList<>();

        asciiValues = addSpecialCharacters(asciiValues);
        Collections.shuffle(asciiValues);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();//adding at least one special character
        //en az bir tane özel karakter ekliyoruz

        asciiValues = addUppercaseCharacters(asciiValues);
        Collections.shuffle(asciiValues);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();//adding at least one uppercase character
        //en az bir tane büyük harf karakter ekliyoruz

        asciiValues = addLowercaseCharacters(asciiValues);
        Collections.shuffle(asciiValues);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear(); //adding at least one lowercase character
        //en az bir tane küçük harf karakter ekliyoruz

        asciiValues = addNumbers(asciiValues);
        Collections.shuffle(asciiValues);
        passwordChars.add((char)(int)asciiValues.get(0)); //adding at least one number
        //en az bir tane sayı ekliyoruz

        asciiValues = addUppercaseCharacters(asciiValues);
        asciiValues = addSpecialCharacters(asciiValues);
        asciiValues = addLowercaseCharacters(asciiValues);
        //adding just uppercase and special characters since numbers are already in the list
        //sadece büyük harfli ve özel karakterleri ekliyoruz çünkü sayılar zaten listede


        Collections.shuffle(asciiValues);

        for(int i=0; i<passwordSize-4; i++){
            passwordChars.add((char)(int)asciiValues.get(i));
        }
        Collections.shuffle(passwordChars);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< passwordSize; i++){
            sb.append(passwordChars.get(i));
        }
        generatedPassword = sb.toString();

        return generatedPassword;
    }

    public String validatePassword(String password){
        if(checkPassword(password)){
            System.out.println("Password validated.");
            return password;
        }else{
            System.out.println("Password invalid.");
            String generatedPassword = autoCreatePassword();
            System.out.println("Generated password: " + generatedPassword);
            return generatedPassword;
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

    public ArrayList<Integer> addSpecialCharacters(ArrayList<Integer> arraylist){
        for(int i=33; i<=47; i++){
            arraylist.add(i);
        }
        for(int i=58; i<=64; i++){
            arraylist.add(i);
        }
        for(int i=91; i<=95; i++){
            arraylist.add(i);
        }
        for(int i=123; i<=126; i++){
            arraylist.add(i);
        }
        return arraylist;
    }

    public ArrayList<Integer> addUppercaseCharacters(ArrayList<Integer> arraylist){
        for(int i=65; i<=90; i++){
            arraylist.add(i);
        }
        return arraylist;
    }

    public ArrayList<Integer> addLowercaseCharacters(ArrayList<Integer> arraylist){
        for(int i=97; i<=122; i++){
            arraylist.add(i);
        }

        return arraylist;
    }

    public ArrayList<Integer> addNumbers(ArrayList<Integer> arraylist){
        for(int i=48; i<=57; i++){
            arraylist.add(i);
        }

        return arraylist;
    }

}
