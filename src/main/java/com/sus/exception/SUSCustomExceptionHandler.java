package com.sus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SUSCustomExceptionHandler {

    @ExceptionHandler(SystemScoreCustomExceptions.CalculateScoreException.class)
    public ResponseEntity<String> computeScoreExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemScoreCustomExceptions.LocalizedSessionScoreException.class)
    public ResponseEntity<String> computeLocalizedSessionExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
