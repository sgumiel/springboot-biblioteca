package com.kairosds.cursospb2.biblioteca.requestlibro.domain.error;

public enum RequestLibroErrors {

    LIBRO_CODE_NOT_EXISTS("RL00001"),
    BIBLIOTECA_CODE_NOT_EXIST("RL00002");

    private String code;

    RequestLibroErrors(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
