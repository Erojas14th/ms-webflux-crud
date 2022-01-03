package com.erojas.reactive.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:06
 * @FileName : HttpErrorInfo.java
 * @Description : Pojo Http information
 */
@Data
@AllArgsConstructor
@Builder
public class HttpErrorInfo {

    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo() {
        this.httpStatus = null;
        this.message = null;
    }
}

