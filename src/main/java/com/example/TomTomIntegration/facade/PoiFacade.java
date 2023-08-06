package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;

public interface PoiFacade {

    PoiResponse getPOI(String place);

    PoiCreationResponse createPOI(PoiCreationRequest poiCreationRequest);
}
