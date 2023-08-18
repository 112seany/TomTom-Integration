package com.example.TomTomIntegration.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String name) {
        super(String.format("Poi with name %s already exists.", name));
    }
}
