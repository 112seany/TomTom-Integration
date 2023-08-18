package com.example.TomTomIntegration.exception;

public class PoiNotFoundException extends RuntimeException {

    public PoiNotFoundException(Long poiId) {
        super(String.format("Poi by id %s was not found", poiId));
    }
}
