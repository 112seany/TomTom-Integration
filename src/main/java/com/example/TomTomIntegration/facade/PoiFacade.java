package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.springframework.data.domain.PageRequest;

public interface PoiFacade {

    PoiTomTomResponse getPoi(String place);

    PoiResponse createPoi(PoiCreationRequest poiCreationRequest);

    PoiResponse getPoiById(Long poiId);

    void deletePoi(Long poiId);

    PoiResponse updatePoi(Long poiId, PoiUpdateRequest request);

    PageablePoiResponse getPoiList(String name, PageRequest pageRequest);
}
