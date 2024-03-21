package com.example.pesto.gateway.config;


import com.example.pesto.gateway.exceptions.ExceptionResponseDTO;
import com.example.pesto.gateway.exceptions.PestoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ PestoException.class })
    public ResponseEntity<ExceptionResponseDTO> projectExceptionHandler(final PestoException ex) {

        if(!ex.getResponseHttpStatus().is4xxClientError()){
            log.error("Project exception occurred", ex);
        }

        ExceptionResponseDTO resp = new ExceptionResponseDTO(ex.getMessage());
        return new ResponseEntity<>(resp, ex.getResponseHttpStatus());
    }

    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<ExceptionResponseDTO> exceptionHandler(final Throwable ex) {
        log.error("Uncaught exception occurred", ex);
        ExceptionResponseDTO resp = new ExceptionResponseDTO("error");
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ExceptionResponseDTO> methodArgumentTypeMismatchExceptionHandler(
            final MethodArgumentTypeMismatchException ex) {
        ExceptionResponseDTO resp = new ExceptionResponseDTO("Improper request");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
