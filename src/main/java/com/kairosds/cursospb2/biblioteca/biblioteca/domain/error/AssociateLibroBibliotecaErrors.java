package com.kairosds.cursospb2.biblioteca.biblioteca.domain.error;

public enum AssociateLibroBibliotecaErrors {

    LIBRO_CODE_NOT_EXISTS("ALB0001"),
    LIBRO_CODE_ALREADY_ASSOCIATE("alb0002");

    private String code;

    AssociateLibroBibliotecaErrors(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
