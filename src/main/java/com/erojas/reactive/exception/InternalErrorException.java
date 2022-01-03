package com.erojas.reactive.exception;

import lombok.Getter;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:16
 * @FileName : NotFoundException.java
 * @Description : 500 Http exception
 */
@Getter
public class InternalErrorException extends GenericException {
    private final String message;

    public InternalErrorException() {
        this.message = null;
    }

    public InternalErrorException(String message) {
        super(message);
        this.message = message;
    }
}
