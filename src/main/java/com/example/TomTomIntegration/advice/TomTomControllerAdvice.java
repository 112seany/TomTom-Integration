package com.example.TomTomIntegration.advice;

import com.example.TomTomIntegration.exception.DuplicateException;
import com.example.TomTomIntegration.exception.PoiNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TomTomControllerAdvice {

    @ExceptionHandler(PoiNotFoundException.class)
    public ResponseEntity<Response> handlePoiNotFoundException(PoiNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Response> handleDuplicateException(DuplicateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response> handleBindException(BindException e) {
        Response response = new Response("Score should be between 0 and 5");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
