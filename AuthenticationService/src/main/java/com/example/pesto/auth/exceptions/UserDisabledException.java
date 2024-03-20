package com.example.pesto.auth.exceptions;

import org.springframework.http.HttpStatus;

public class UserDisabledException extends PestoException {
    public UserDisabledException(final Throwable cause) {
        super("Exception User is disabled", HttpStatus.UNAUTHORIZED, cause);
    }

    public UserDisabledException() {
        this(null);
    }
}
