package com.example.TomTomIntegration.rest.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PoiNotFoundExceptionExample {

    @Schema(example = "Poi by id 1 was not found")
    private String message;
}
