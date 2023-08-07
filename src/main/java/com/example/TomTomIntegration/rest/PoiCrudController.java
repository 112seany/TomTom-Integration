package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    public PoiResponse createPOI(@RequestBody @Valid PoiCreationRequest request) {
        return poiFacade.createPOI(request);
    }

    @GetMapping("/{poiID}")
    public PoiResponse getPOIbyID(@PathVariable(value = "poiID") Long poiID) {
        return poiFacade.getPOIbyID(poiID);
    }

    @PutMapping("/{poiID}")
    public PoiResponse updatePOI(@PathVariable(value = "poiID") Long poiID, @RequestBody @Valid PoiUpdateRequest request) {
        return poiFacade.updatePOI(poiID, request);
    }

    @DeleteMapping("/{poiID}")
    public void deletePOI(@PathVariable(value = "poiID") Long poiID) {
        poiFacade.deletePOI(poiID);
    }
}
