package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.CreatePoiAPI;
import com.example.TomTomIntegration.rest.swagger.DeletePoiAPI;
import com.example.TomTomIntegration.rest.swagger.GetPoiByIdAPI;
import com.example.TomTomIntegration.rest.swagger.UpdatePoiAPI;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poi/place")
public class PoiCrudController {

    private final PoiFacade poiFacade;

    @CreatePoiAPI
    @PostMapping
    public PoiResponse createPOI(@RequestBody @Valid PoiCreationRequest request) {
        return poiFacade.createPOI(request);
    }

    @GetPoiByIdAPI
    @GetMapping("/{poiId}")
    public PoiResponse getPOIbyID(@PathVariable(value = "poiId") Long poiId) {
        return poiFacade.getPOIbyID(poiId);
    }

    @UpdatePoiAPI
    @PutMapping("/{poiId}")
    public PoiResponse updatePOI(@PathVariable(value = "poiId") Long poiId, @RequestBody @Valid PoiUpdateRequest request) {
        return poiFacade.updatePOI(poiId, request);
    }

    @DeletePoiAPI
    @DeleteMapping("/{poiId}")
    public void deletePOI(@PathVariable(value = "poiId") Long poiId) {
        poiFacade.deletePOI(poiId);
    }
}
