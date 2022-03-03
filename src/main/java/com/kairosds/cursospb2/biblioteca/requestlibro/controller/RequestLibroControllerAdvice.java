package com.kairosds.cursospb2.biblioteca.requestlibro.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.libro.domain.error.CreateLibroErrors;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.error.RequestLibroErrors;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroBibliotecaNotExist;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroLibroNotExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestLibroControllerAdvice {

    @ExceptionHandler(RequestLibroLibroNotExist.class)
    public ResponseEntity<ApiError> handleRequestLibroLibroNotExist(RequestLibroLibroNotExist exception) {

        final var apiError = ApiError.builder()
                .code(RequestLibroErrors.LIBRO_CODE_NOT_EXISTS.getCode())
                .message(exception.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(RequestLibroBibliotecaNotExist.class)
    public ResponseEntity<ApiError> handleRequestLibroLibroNotExist(RequestLibroBibliotecaNotExist exception) {

        final var apiError = ApiError.builder()
                .code(RequestLibroErrors.BIBLIOTECA_CODE_NOT_EXIST.getCode())
                .message(exception.getMessage()).build();

        return ResponseEntity.status(400).body(apiError);
    }
}
