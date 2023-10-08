package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PoiService {

    PoiTomTomResponse getPoi(String place);

    PoiResponse createPoi(PoiCreationRequest poiCreationRequest);

    PoiResponse getPoiById(Long poiId);

    void deletePoi(Long poiId);

    PoiResponse updatePoi(Long poiId, PoiUpdateRequest request);

    List<PoiResponse> getPoiList(String name, PageRequest pageRequest);
}
