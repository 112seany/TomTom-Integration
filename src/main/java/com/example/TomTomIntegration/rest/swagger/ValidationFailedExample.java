package com.example.TomTomIntegration.rest.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ValidationFailedExample {

    @Schema(example = "Validation failed")
    private String message;

    @Schema(example = "Latitude should be between -90 and 90, Longitude should be between -180 and 180")
    private String[] errors;
}
