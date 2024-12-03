package com.auth.configuration;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        // Log the exception (optional)
        // Logger.error("Database connection error", ex);

        // Return a custom error response
        return new ResponseEntity<>("Database connection error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Outros handlers de exceção podem ser adicionados aqui
}