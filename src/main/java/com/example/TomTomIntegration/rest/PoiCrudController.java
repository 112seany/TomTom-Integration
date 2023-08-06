package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poi/place")
public class PoiCrudController {

    private final PoiFacade poiFacade;

    @Operation(summary = "Create POI", description = "Returns created POI")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created")})
    @PostMapping
    public PoiCreationResponse createPOI(@RequestBody PoiCreationRequest request) {
        return poiFacade.createPOI(request);
    }
}
