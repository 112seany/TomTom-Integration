package com.example.TomTomIntegration.rest.swagger;


import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.example.PoiNotFoundExceptionExample;
import com.example.TomTomIntegration.rest.swagger.example.PoiResponseExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.PoiNotFoundExceptionExample.POI_NOT_FOUND_EXCEPTION;
import static com.example.TomTomIntegration.rest.swagger.example.PoiResponseExample.POI_RESPONSE;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Operation(summary = "Get POI", description = "Returns POI by given ID")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully received", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = PoiResponseExample.class),
                examples = {@ExampleObject(name = "Successfully received", value = POI_RESPONSE)})}),
        @ApiResponse(responseCode = "404", description = "Poi by id 1 was not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PoiNotFoundExceptionExample.class),
                        examples = {@ExampleObject(name = "Request is not valid", value = POI_NOT_FOUND_EXCEPTION)})
        })})
public @interface GetPoiByIdAPI {
}
