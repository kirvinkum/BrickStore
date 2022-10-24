package com.example.brickstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestException> handleNotValidArgsException(MethodArgumentNotValidException ex) {
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return buildResponseEntity(new RestException(HttpStatus.NOT_ACCEPTABLE, "Invalid or Missing Parameter(s).", errors));
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<RestException> invalidInputException(InvalidInputException ex) {
        return buildResponseEntity(new RestException (HttpStatus.NOT_ACCEPTABLE, ex.getMessage ()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<RestException> handleCustomException(CustomException exception) {
        return buildResponseEntity(new RestException (BAD_REQUEST, exception));
    }


    private ResponseEntity<RestException> buildResponseEntity(RestException restException) {
        return new ResponseEntity<>(restException, restException.getStatus());
    }
}
