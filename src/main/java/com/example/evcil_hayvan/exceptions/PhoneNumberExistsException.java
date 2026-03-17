package com.example.evcil_hayvan.exceptions;

public class PhoneNumberExistsException extends RuntimeException {
    public PhoneNumberExistsException() {
        super("Bu telefon numarası başka bir kullanıcı tarafından kullanılıyor.");
    }
}
