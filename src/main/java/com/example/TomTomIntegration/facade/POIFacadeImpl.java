package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.response.PoiResponse;
import com.example.TomTomIntegration.service.POIService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class POIFacadeImpl implements POIFacade {

    private final POIService poiService;

    @Override
    public PoiResponse getPOI(String place) {
        return poiService.getPOI(place);
    }
}
