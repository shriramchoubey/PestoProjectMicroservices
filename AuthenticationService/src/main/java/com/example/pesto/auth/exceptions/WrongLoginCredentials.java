package com.example.pesto.auth.exceptions;

import org.springframework.http.HttpStatus;

public class WrongLoginCredentials extends PestoException {
    public WrongLoginCredentials(final Throwable cause) {
        super("Wrong login Credentials", HttpStatus.UNAUTHORIZED, null);

    }
    public WrongLoginCredentials(final String message, final Throwable cause) {
        super(message, HttpStatus.UNAUTHORIZED, null);

    }

    public WrongLoginCredentials(){
        this(null);
    }
}