package com.example.TomTomIntegration.rest.swagger;

import com.example.TomTomIntegration.rest.swagger.example.PoiResponseExample;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.GetPoiListExample.NAME_FOR_FILTER;
import static com.example.TomTomIntegration.rest.swagger.example.GetPoiListExample.RESPONSE_EXAMPLE;
import static com.example.TomTomIntegration.rest.swagger.example.PageSizeExceptionExample.SIZE_EXCEPTION_EXAMPLE;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RequestBody(description = "Name to filter", required = true,
        content = @Content(schema = @Schema(implementation = String.class),
                examples = {@ExampleObject(value = NAME_FOR_FILTER)}))
@Operation(summary = "Get list of POI", description = "Returns POI according to specified parameters(page, size, name for filter)")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully received", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PoiResponseExample.class),
                        examples = {@ExampleObject(name = "Successfully received", value = RESPONSE_EXAMPLE)})}),
        @ApiResponse(responseCode = "400", description = "Page size must not be less than one", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentException.class),
                        examples = {@ExampleObject(name = "Request is not valid", value = SIZE_EXCEPTION_EXAMPLE)})})})
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page.")})
public @interface GetPoiList {
}
