package com.erojas.reactive.exception;

import lombok.Getter;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:17
 * @FileName : BadRequestException.java
 * @Description : 400 Http exception
 */
@Getter
public class BadRequestException extends GenericException {
    private final String message;

    public BadRequestException() {
        this.message = null;
    }

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
