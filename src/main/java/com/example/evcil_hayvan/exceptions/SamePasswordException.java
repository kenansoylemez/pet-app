package com.example.evcil_hayvan.exceptions;

public class SamePasswordException extends RuntimeException {

    public SamePasswordException(){
        super("Eski ve yeni şifreler aynı olamaz.");
    }

}
