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
public class PoiNotFoundExceptionSwaggerExample {

    @Schema(example = "Poi by id 1 was not found")
    private String message;
}
