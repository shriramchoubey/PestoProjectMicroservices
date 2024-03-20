package com.example.pesto.auth.exceptions;

import org.springframework.http.HttpStatus;

public class ProjectUserAlreadyExist extends PestoException {
    public ProjectUserAlreadyExist(final Throwable cause) {
        super("User Already Exist", HttpStatus.BAD_REQUEST, cause);
    }

    public ProjectUserAlreadyExist(){
        this(null);
    }
}
