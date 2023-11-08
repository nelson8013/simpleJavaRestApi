package com.example.RestApi.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {



    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus status, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus status){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);

        return new ResponseEntity<>(response, status);
    }
}

