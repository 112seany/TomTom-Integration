package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.service.PoiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PoiFacadeImpl implements PoiFacade {

    private final PoiService poiService;

    @Override
    public PoiResponse getPOI(String place) {
        return poiService.getPOI(place);
    }

    @Override
    public PoiCreationResponse createPOI(PoiCreationRequest poiCreationRequest) {
        return poiService.createPOI(poiCreationRequest);
    }

}
