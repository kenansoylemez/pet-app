package com.example.evcil_hayvan.exceptions;

public class NoUserException extends RuntimeException {
    public NoUserException() {
        super("Böyle bir kullanıcı yok.");
    }
}
