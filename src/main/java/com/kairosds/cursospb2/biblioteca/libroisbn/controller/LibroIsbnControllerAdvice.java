package com.kairosds.cursospb2.biblioteca.libroisbn.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.error.CreateLibroIsbnErrors;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNCreditsMaximun;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibroIsbnControllerAdvice {

    @ExceptionHandler(CreateLibroISBNExists.class)
    public ResponseEntity<ApiError> handleCreateLibroISBNExists(CreateLibroISBNExists excpetion) {

        final var apiError = ApiError.builder()
                .code(CreateLibroIsbnErrors.ISBN_EXISTS.getCode())
                .message(excpetion.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(CreateLibroISBNCreditsMaximun.class)
    public ResponseEntity<ApiError> handleCreateLibroISBNCreditsMaximun(CreateLibroISBNCreditsMaximun excpetion) {

        final var apiError = ApiError.builder()
                .code(CreateLibroIsbnErrors.CREDITS_MAX_REACHED.getCode())
                .message(excpetion.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }
}
