package com.erojas.reactive.exception;

import com.erojas.reactive.http.HttpErrorInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:09
 * @FileName : GlobalControllerExceptionHandler.java
 * @Description : Http request interceptor
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    HttpErrorInfo handleNotFoundException(NotFoundException ex) {
        return  HttpErrorInfo.builder().httpStatus(NOT_FOUND).message(ex.getMessage()).build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody
    HttpErrorInfo handleBadRequestException(BadRequestException ex) {
        return  HttpErrorInfo.builder().httpStatus(BAD_REQUEST).message(ex.getMessage()).build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalErrorException.class)
    public @ResponseBody
    HttpErrorInfo handleInternalErrorException(InternalErrorException ex) {
        return  HttpErrorInfo.builder().httpStatus(INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    HttpErrorInfo handleException(Exception ex) {
        return  HttpErrorInfo.builder().httpStatus(INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
    }
}
