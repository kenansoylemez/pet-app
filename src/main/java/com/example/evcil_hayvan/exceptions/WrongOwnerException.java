package com.example.evcil_hayvan.exceptions;

public class WrongOwnerException extends RuntimeException {

    public WrongOwnerException() {
        super("Hayvan ve sahip eşleşmiyor.");
    }
}
