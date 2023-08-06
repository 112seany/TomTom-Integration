package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;

public interface PoiService {

    PoiResponse getPOI(String place);

    PoiCreationResponse createPOI(PoiCreationRequest poiCreationRequest);
}
