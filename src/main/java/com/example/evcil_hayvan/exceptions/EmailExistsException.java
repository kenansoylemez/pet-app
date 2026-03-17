package com.example.evcil_hayvan.exceptions;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException() {
        super("Bu email adresi başka bir kullanıcı tarafından kullanılıyor.");
    }
}
