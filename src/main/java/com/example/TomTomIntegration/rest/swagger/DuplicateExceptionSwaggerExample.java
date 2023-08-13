package com.example.TomTomIntegration.rest.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateExceptionSwaggerExample {

    @Schema(example = "Poi with name 'Restaurant Bavaria' already exists")
    private String message;
}
