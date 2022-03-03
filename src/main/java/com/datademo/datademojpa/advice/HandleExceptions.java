package com.datademo.datademojpa.advice;

import com.datademo.datademojpa.exceptions.DBDBadRequestException;
import com.datademo.datademojpa.exceptions.DBDResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(DBDResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(
            DBDResourceNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DBDBadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(
            DBDBadRequestException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
