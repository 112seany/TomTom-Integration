package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "POI API")
public class PoiController {

    private final PoiFacade poiFacade;

    @Operation(summary = "Get a POI by name", description = "Returns a list of POIs by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved")})
    @GetMapping("/poi/{place}")
    public PoiResponse getPOI(@Parameter(name = "/place", description = "Place name", example = "Restaurant")
                                  @PathVariable(value = "place") String place) {
        return poiFacade.getPOI(place);
    }
}