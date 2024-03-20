package com.example.pesto.productManagement.config;


import com.example.pesto.productManagement.exceptions.ExceptionResponseDTO;
import com.example.pesto.productManagement.exceptions.PestoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<String> missingServletRequestParameterException(
            final MethodArgumentTypeMismatchException ex) {
        String message = "{\"message\": \" Required params missing : "+ex.getParameter()+ "\"}";
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MissingServletRequestPartException.class })
    public ResponseEntity<String> MissingServletRequestPartException(
            final MissingServletRequestPartException ex) {
        String message = "{\"message\": \" Required params missing : "+ex.getRequestPartName()+ "\"}";
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> maxUploadSizeExceededException(
            MaxUploadSizeExceededException ex) {
        return new ResponseEntity<>("{\"message\": \"Max file size is 5 mb\"}", HttpStatus.PAYLOAD_TOO_LARGE);
    }


}
