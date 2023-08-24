package com.example.TomTomIntegration.rest.swagger;

import com.example.TomTomIntegration.rest.swagger.example.PoiTomTomResponseExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.PoiTomTomResponseExample.GET_POI_SUCCESSFULLY;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Operation(summary = "Get a POI by name", description = "Returns a list of POIs by name",
        responses = {@ApiResponse(responseCode = "200", description = "Successfully received", content =
        @Content(mediaType = "application/json", schema = @Schema(implementation = PoiTomTomResponseExample.class),
                examples = {@ExampleObject(name = "Successfully received", value = GET_POI_SUCCESSFULLY)}))})
public @interface GetPoiAPI {
}
