package com.kairosds.cursospb2.biblioteca.apierror;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ApiError implements Serializable {

    private String code;

    private String message;
}
