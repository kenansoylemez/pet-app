package com.example.evcil_hayvan.exceptions;

public class SameEmailException extends RuntimeException {
    public SameEmailException() {
        super("Eski ve yeni email aynı olamaz.");
    }
}
