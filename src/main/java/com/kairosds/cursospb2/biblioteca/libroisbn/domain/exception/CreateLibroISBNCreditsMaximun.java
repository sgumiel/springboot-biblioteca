package com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception;

import lombok.Data;

@Data
public class CreateLibroISBNCreditsMaximun extends RuntimeException {

    private Integer credits;

    private Integer creditsMaximun;

    public CreateLibroISBNCreditsMaximun(Integer credits, Integer creditsMaximun) {
        super("The maximun credits is: " + creditsMaximun + ". The request was: " + credits);
        this.credits = credits;
        this.creditsMaximun = creditsMaximun;
    }
}
