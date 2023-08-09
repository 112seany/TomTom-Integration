package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;

public interface PoiFacade {

    PoiTomTomResponse getPOI(String place);

    PoiResponse createPOI(PoiCreationRequest poiCreationRequest);

    PoiResponse getPOIbyID(Long poiID);

    void deletePOI(Long poiID);

    PoiResponse updatePOI(Long poiID, PoiUpdateRequest request);
}
