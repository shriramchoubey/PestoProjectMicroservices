package com.example.pesto.exceptions;

import org.springframework.http.HttpStatus;

public class PestoUnAuthorisedUserException extends PestoException {
    public PestoUnAuthorisedUserException(final Throwable cause) {
        super("User is not authorised to do such operation", HttpStatus.UNAUTHORIZED, cause);
    }

    public PestoUnAuthorisedUserException(){
        this(null);
    }
}
