package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepcionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail manejarRecursoNoEncontrado(ResourceNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ProblemDetail manejarRecursoYaExistente(ResourceAlreadyExistsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail manejarArgumentoInvalido(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}