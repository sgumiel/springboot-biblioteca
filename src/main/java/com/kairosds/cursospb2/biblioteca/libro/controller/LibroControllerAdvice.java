package com.kairosds.cursospb2.biblioteca.libro.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.libro.domain.error.CreateLibroErrors;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroCodeExists;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroIsbnNotExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibroControllerAdvice {

    @ExceptionHandler(CreateLibroIsbnNotExists.class)
    public ResponseEntity<ApiError> handleCreateLibroIsbnNotExists(CreateLibroIsbnNotExists excpetion) {

        final var apiError = ApiError.builder()
                .code(CreateLibroErrors.ISBN_NOT_EXISTS.getCode())
                .message(excpetion.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(CreateLibroCodeExists.class)
    public ResponseEntity<ApiError> handleCreateLibroISBNCreditsMaximun(CreateLibroCodeExists excpetion) {

        final var apiError = ApiError.builder()
                .code(CreateLibroErrors.CODE_EXISTS.getCode())
                .message(excpetion.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }
}
