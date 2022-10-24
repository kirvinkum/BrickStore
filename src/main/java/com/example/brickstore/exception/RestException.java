package com.example.brickstore.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestException {

    private HttpStatus status;

    private String message;

    private List<String> errors = new ArrayList<>();

    private Instant timestamp;

    public RestException(HttpStatus status, Exception exception) {
        this.status = status;
        this.message = exception.getMessage();
        this.timestamp = Instant.now();
    }

    public RestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public RestException(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = Instant.now();
    }


}
