package com.example.TomTomIntegration.rest.swagger;

import com.example.TomTomIntegration.rest.swagger.example.CreatePoiRequestBodyExample;
import com.example.TomTomIntegration.rest.swagger.example.CreatedPoiExample;
import com.example.TomTomIntegration.rest.swagger.example.DuplicateExceptionExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.CreatePoiRequestBodyExample.REQUEST_BODY_CREATE_POI;
import static com.example.TomTomIntegration.rest.swagger.example.CreatedPoiExample.CREATED_POI;
import static com.example.TomTomIntegration.rest.swagger.example.DuplicateExceptionExample.GET_NEARBY_POI_DUPLICATE_EXCEPTION;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RequestBody(description = "Place to add", required = true,
        content = @Content(schema = @Schema(implementation = CreatePoiRequestBodyExample.class),
                examples = {@ExampleObject(value = REQUEST_BODY_CREATE_POI)}))
@Operation(summary = "Create POI", description = "Returns created POI")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreatedPoiExample.class),
                examples = {@ExampleObject(name = "Successfully created",value = CREATED_POI)})}),
        @ApiResponse(responseCode = "404", description = "Poi with name 'Restaurant Bavaria' already exists", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DuplicateExceptionExample.class),
                        examples = {@ExampleObject(name = "Request is not valid",value = GET_NEARBY_POI_DUPLICATE_EXCEPTION)})
        })})
public @interface CreatePoiAPI {
}

