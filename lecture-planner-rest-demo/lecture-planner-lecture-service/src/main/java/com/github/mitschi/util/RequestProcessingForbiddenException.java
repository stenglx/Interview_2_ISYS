package com.github.mitschi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class RequestProcessingForbiddenException extends Exception{

    private static final long serialVersionUID = 1L;

    public RequestProcessingForbiddenException(String message){
        super(message);
    }
}