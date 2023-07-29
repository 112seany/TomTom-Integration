package com.example.TomTomIntegration.controller;

import com.example.TomTomIntegration.facade.POIFacade;
import com.example.TomTomIntegration.response.PoiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class POIController {

    private final POIFacade poiFacade;

    @GetMapping("/poi/{place}")
    public PoiResponse getPOI(@PathVariable(value = "place") String place) {
        return poiFacade.getPOI(place);
    }
}