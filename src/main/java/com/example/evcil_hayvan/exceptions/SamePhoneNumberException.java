package com.example.evcil_hayvan.exceptions;

public class SamePhoneNumberException extends RuntimeException {
    public SamePhoneNumberException() {
        super("Eski ve yeni telefon numarası aynı olamaz.");
    }
}
