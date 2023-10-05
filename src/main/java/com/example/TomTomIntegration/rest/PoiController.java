package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import com.example.TomTomIntegration.rest.swagger.GetPoiAPI;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "POI API")
public class PoiController {

    private final PoiFacade poiFacade;

    @GetPoiAPI
    @GetMapping("/poi/{place}")
    public PoiTomTomResponse getPoi(@Parameter(name = "/place", description = "Place name", example = "Restaurant")
                                  @PathVariable(value = "place") String place) {
        return poiFacade.getPoi(place);
    }
}