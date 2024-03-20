package com.example.pesto.auth.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class PestoException extends RuntimeException {

    @Getter
    private final HttpStatus responseHttpStatus;

    public PestoException(final String message, final HttpStatus responseHttpStatus, final Throwable cause) {
        super(message, cause);
        this.responseHttpStatus = responseHttpStatus;
    }

}
