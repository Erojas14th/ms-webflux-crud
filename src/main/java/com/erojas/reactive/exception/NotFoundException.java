package com.erojas.reactive.exception;

import lombok.Getter;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:16
 * @FileName : NotFoundException.java
 * @Description : 404 Http exception
 */
@Getter
public class NotFoundException extends GenericException {
    private final String message;

    public NotFoundException() {
        this.message = null;
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
