package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.DuplicateExceptionExample;
import com.example.TomTomIntegration.rest.swagger.PoiNotFoundExceptionExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Poi with name 'Restaurant Bavaria' already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DuplicateExceptionExample.class))
            })})
    @PostMapping
    public PoiResponse createPOI(@RequestBody @Valid PoiCreationRequest request) {
        return poiFacade.createPOI(request);
    }

    @Operation(summary = "Get POI", description = "Returns POI by given ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully received", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PoiResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Poi by id 1 was not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PoiNotFoundExceptionExample.class))
            })})
    @GetMapping("/{poiId}")
    public PoiResponse getPOIbyID(@PathVariable(value = "poiId") Long poiId) {
        return poiFacade.getPOIbyID(poiId);
    }

    @Operation(summary = "Update POI", description = "Update POI by given ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PoiResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Poi by id 1 was not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PoiNotFoundExceptionExample.class))
            })})
    @PutMapping("/{poiId}")
    public PoiResponse updatePOI(@PathVariable(value = "poiId") Long poiId, @RequestBody @Valid PoiUpdateRequest request) {
        return poiFacade.updatePOI(poiId, request);
    }

    @Operation(summary = "Delete POI", description = "Delete POI by given ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Poi by id 1 was not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PoiNotFoundExceptionExample.class))
            })})
    @DeleteMapping("/{poiId}")
    public void deletePOI(@PathVariable(value = "poiId") Long poiId) {
        poiFacade.deletePOI(poiId);
    }
}
