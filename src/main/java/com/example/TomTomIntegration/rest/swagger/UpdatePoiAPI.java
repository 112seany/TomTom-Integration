package com.example.TomTomIntegration.rest.swagger;

import com.example.TomTomIntegration.rest.swagger.example.CreatePoiRequestBodyExample;
import com.example.TomTomIntegration.rest.swagger.example.PoiNotFoundExceptionExample;
import com.example.TomTomIntegration.rest.swagger.example.PoiResponseExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.CreatePoiRequestBodyExample.REQUEST_BODY_CREATE_POI;
import static com.example.TomTomIntegration.rest.swagger.example.PoiNotFoundExceptionExample.POI_NOT_FOUND_EXCEPTION;
import static com.example.TomTomIntegration.rest.swagger.example.PoiResponseExample.POI_RESPONSE;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RequestBody(description = "Place to update", required = true,
        content = @Content(schema = @Schema(implementation = CreatePoiRequestBodyExample.class),
                examples = {@ExampleObject(value = REQUEST_BODY_CREATE_POI)}))
@Operation(summary = "Update POI", description = "Update POI by given ID")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully updated", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = PoiResponseExample.class),
                examples = {@ExampleObject(name = "Successfully updated", value = POI_RESPONSE)})}),
        @ApiResponse(responseCode = "404", description = "Poi by id 1 was not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PoiNotFoundExceptionExample.class),
                        examples = {@ExampleObject(name = "Request is not valid", value = POI_NOT_FOUND_EXCEPTION)})
        })})
public @interface UpdatePoiAPI {
}
