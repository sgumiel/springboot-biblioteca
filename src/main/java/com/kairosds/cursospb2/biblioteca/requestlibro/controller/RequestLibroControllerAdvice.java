package com.kairosds.cursospb2.biblioteca.requestlibro.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestLibroControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        return ResponseEntity.ok(ApiError.builder().build());
    }
}
