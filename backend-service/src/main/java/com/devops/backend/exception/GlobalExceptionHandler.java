package com.devops.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> runtime(
            RuntimeException ex){

        Map<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validation(
            MethodArgumentNotValidException ex){

        Map<String,String> map=new HashMap<>();

        map.put("message","Validation Failed");

        return ResponseEntity
                .badRequest()
                .body(map);

    }

}
