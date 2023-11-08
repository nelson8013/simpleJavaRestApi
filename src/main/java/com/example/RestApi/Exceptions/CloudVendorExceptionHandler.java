package com.example.RestApi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CloudVendorExceptionHandler {


    @ExceptionHandler( value = {CloudVendorNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException){
      CloudVendorException exception = new CloudVendorException(
              cloudVendorNotFoundException.getMessage(),
              cloudVendorNotFoundException.getCause(),
              HttpStatus.NOT_FOUND
      );
      return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
