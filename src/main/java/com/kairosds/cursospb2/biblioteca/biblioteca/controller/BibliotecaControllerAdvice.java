package com.kairosds.cursospb2.biblioteca.biblioteca.controller;

import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BibliotecaControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e){
        return ResponseEntity.ok(ApiError.builder().build());
    }

}
