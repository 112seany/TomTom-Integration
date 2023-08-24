package com.example.TomTomIntegration.rest.swagger;

import com.example.TomTomIntegration.rest.swagger.example.NearbySearchInfoResponseExample;
import com.example.TomTomIntegration.rest.swagger.example.ValidationFailedExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

import static com.example.TomTomIntegration.rest.swagger.example.NearbySearchInfoResponseExample.GET_NEARBY_POI_SUCCESSFULLY;
import static com.example.TomTomIntegration.rest.swagger.example.ValidationFailedExample.GET_NEARBY_POI_INVALID_PAYLOAD;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Operation(summary = "Get POI", description = "Returns nearby POI",
        responses = {@ApiResponse(responseCode = "200", description = "Successfully received", content =
        @Content(mediaType = "application/json", schema = @Schema(implementation = NearbySearchInfoResponseExample.class),
                examples = {@ExampleObject(name = "Successfully received", value = GET_NEARBY_POI_SUCCESSFULLY)})),
                @ApiResponse(responseCode = "400", description = "Validation failed", content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationFailedExample.class),
                        examples = {@ExampleObject(name = "Request is not valid", value = GET_NEARBY_POI_INVALID_PAYLOAD)}))})
public @interface GetNearbyPoiAPI {
}
