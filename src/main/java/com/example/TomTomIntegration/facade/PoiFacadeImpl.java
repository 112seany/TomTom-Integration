package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import com.example.TomTomIntegration.service.PoiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PoiFacadeImpl implements PoiFacade {

    private final PoiService poiService;

    @Override
    public PoiTomTomResponse getPOI(String place) {
        return poiService.getPOI(place);
    }

    @Override
    public PoiResponse createPOI(PoiCreationRequest poiCreationRequest) {
        return poiService.createPOI(poiCreationRequest);
    }

    @Override
    public PoiResponse getPOIbyID(Long poiID) {
        return poiService.getPOIbyId(poiID);
    }

    @Override
    public void deletePOI(Long poiID) {
        poiService.deletePOI(poiID);
    }

    @Override
    public PoiResponse updatePOI(Long poiID, PoiUpdateRequest request) {
        return poiService.updatePOI(poiID, request);
    }


}
