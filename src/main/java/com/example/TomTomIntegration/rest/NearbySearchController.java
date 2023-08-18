package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.NearbySearchFacade;
import com.example.TomTomIntegration.rest.response.NearbySearchInfoResponse;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import com.example.TomTomIntegration.rest.swagger.PoiNotFoundExceptionExample;
import com.example.TomTomIntegration.rest.swagger.ValidationFailedExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class NearbySearchController {

    private final NearbySearchFacade nearbySearchFacade;

    @Operation(summary = "Get a POI by given coordinates, longitude and latitude",
            description = "Returns a list of POIs by given coordinates, longitude and latitude")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully received", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NearbySearchInfoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationFailedExample.class))})})
    @GetMapping("/poi/nearby-search")
    public NearbySearchResponse getNearbyPOI(@RequestParam(value = "lat")
                                             @DecimalMin(value = "-90", message = "Latitude should be between -90 and 90")
                                             @DecimalMax(value = "90", message = "Latitude should be between -90 and 90") Double latitude,
                                             @RequestParam(value = "lon")
                                             @DecimalMin(value = "-180", message = "Longitude should be between -180 and 180")
                                             @DecimalMax(value = "180", message = "Longitude should be between -180 and 180") Double longitude) {

        return nearbySearchFacade.getNearbyPOI(latitude, longitude);
    }
}
