package com.kairosds.cursospb2.biblioteca.libro.domain.error;

public enum CreateLibroErrors {

    ISBN_NOT_EXISTS("CLE00001"),
    CODE_EXISTS("CLE0002");

    private String code;

    CreateLibroErrors(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
