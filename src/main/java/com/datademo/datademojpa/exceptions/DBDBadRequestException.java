package com.datademo.datademojpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DBDBadRequestException extends RuntimeException{
    public DBDBadRequestException(String message){
        super(message);
    }
}
