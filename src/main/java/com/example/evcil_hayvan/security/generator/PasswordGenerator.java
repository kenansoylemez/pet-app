package com.example.evcil_hayvan.security.generator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class PasswordGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

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

    public String autoCreatePassword(){
        ArrayList<Integer> asciiValues = new ArrayList<>();
        int passwordSize = secureRandom.nextInt(6) + 10;
        ArrayList<Character> passwordChars = new ArrayList<>();

        asciiValues = addSpecialCharacters(asciiValues);
        Collections.shuffle(asciiValues, secureRandom);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();
        //adding at least one special character
        //en az bir tane özel karakter ekliyoruz

        asciiValues = addUppercaseCharacters(asciiValues);
        Collections.shuffle(asciiValues, secureRandom);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();
        //adding at least one uppercase character
        //en az bir tane büyük harf karakter ekliyoruz

        asciiValues = addLowercaseCharacters(asciiValues);
        Collections.shuffle(asciiValues, secureRandom);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();
        //adding at least one lowercase character
        //en az bir tane küçük harf karakter ekliyoruz

        asciiValues = addNumbers(asciiValues);
        Collections.shuffle(asciiValues, secureRandom);
        passwordChars.add((char)(int)asciiValues.get(0));
        asciiValues.clear();
        //adding at least one number
        //en az bir tane sayı ekliyoruz

        asciiValues = addUppercaseCharacters(asciiValues);
        asciiValues = addSpecialCharacters(asciiValues);
        asciiValues = addLowercaseCharacters(asciiValues);
        asciiValues = addNumbers(asciiValues);
        //sadece büyük harfli ve özel karakterleri ekliyoruz çünkü sayılar zaten listede


        Collections.shuffle(asciiValues, secureRandom);

        for(int i=0; i<passwordSize-4; i++){
            passwordChars.add((char)(int)asciiValues.get(i));
        }
        Collections.shuffle(passwordChars, secureRandom);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< passwordSize; i++){
            sb.append(passwordChars.get(i));
        }
        return sb.toString();
    }

}
