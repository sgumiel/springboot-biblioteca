package com.kairosds.cursospb2.biblioteca.libroisbn.domain.error;

public enum CreateLibroIsbnErrors {

    ISBN_EXISTS("CLIE0001"),
    CREDITS_MAX_REACHED("CLIE002");

    private String code;

    CreateLibroIsbnErrors(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
