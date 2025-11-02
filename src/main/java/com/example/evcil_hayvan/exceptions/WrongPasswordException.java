package com.example.evcil_hayvan.exceptions;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException(){
        super("Girilen şifre yanlış.");
    }

}
