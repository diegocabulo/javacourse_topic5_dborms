package com.datademo.datademojpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DBDResourceNotFoundException  extends RuntimeException{
    public DBDResourceNotFoundException(String message){
        super(message);
    }
}
