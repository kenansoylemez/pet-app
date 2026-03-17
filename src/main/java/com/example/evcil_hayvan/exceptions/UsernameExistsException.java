package com.example.evcil_hayvan.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
        super("Bu kullanıcı adı başka bir kullanıcı tarafından kullanılıyor.");
    }
}
