package com.kairosds.cursospb2.biblioteca.biblioteca.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.error.AssociateLibroBibliotecaErrors;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroAlreadyAssociated;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroCodeNotExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BibliotecaControllerAdvice {

    @ExceptionHandler(AssociateLibroBiliotecaLibroCodeNotExist.class)
    public ResponseEntity<ApiError> handleAssociateLibroBiliotecaLibroCodeNotExist(AssociateLibroBiliotecaLibroCodeNotExist exception) {

        final var apiError = ApiError.builder()
                .code(AssociateLibroBibliotecaErrors.LIBRO_CODE_NOT_EXISTS.getCode())
                .message(exception.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);

    }

    @ExceptionHandler(AssociateLibroBiliotecaLibroAlreadyAssociated.class)
    public ResponseEntity<ApiError> handleAssociateLibroBiliotecaLibroAlreadyAssociated(AssociateLibroBiliotecaLibroAlreadyAssociated exception) {

        final var apiError = ApiError.builder()
                .code(AssociateLibroBibliotecaErrors.LIBRO_CODE_ALREADY_ASSOCIATE.getCode())
                .message(exception.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);

    }

}
