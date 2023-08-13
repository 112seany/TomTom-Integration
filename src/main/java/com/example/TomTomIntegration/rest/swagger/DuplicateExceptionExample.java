package com.example.TomTomIntegration.rest.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DuplicateExceptionExample {

    @Schema(example = "Poi with name 'Restaurant Bavaria' already exists")
    private String message;
}
