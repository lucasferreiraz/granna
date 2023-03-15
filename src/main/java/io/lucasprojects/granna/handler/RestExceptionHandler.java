package io.lucasprojects.granna.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.lucasprojects.granna.domain.exception.ErrorResponse;
import io.lucasprojects.granna.domain.exception.ResourceNotFoundException;
import io.lucasprojects.granna.utils.DateConverter;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException e) {

        String dateTime = DateConverter.parse(new Date());

        ErrorResponse error = new ErrorResponse(
            dateTime, 
            HttpStatus.NOT_FOUND.value(), 
            "Not found", 
            e.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerResourceException(Exception e) {

        String dateTime = DateConverter.parse(new Date());

        ErrorResponse error = new ErrorResponse(
            dateTime, 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Internal Server Error", 
            e.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
