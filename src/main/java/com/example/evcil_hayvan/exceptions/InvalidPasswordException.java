package com.example.evcil_hayvan.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Şifreniz en az bir büyük harf, bir küçük harf, bir rakam ve" +
                "bir özel karakter içermelidir ve en az 8 karakter olmalıdır.");
    }
}
