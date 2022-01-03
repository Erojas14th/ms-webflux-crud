package com.erojas.reactive.exception;

import lombok.Getter;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:11
 * @FileName : GenericException.java
 * @Description : Generic exception
 */
@Getter
public class GenericException extends RuntimeException{
    private final  String message;

    public GenericException(){
        this.message = null;
    }

    public GenericException(String message) {
        super(message);
        this.message = message;
    }
}
