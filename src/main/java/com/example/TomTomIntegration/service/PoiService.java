package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PoiService {

    PoiTomTomDTO getPoi(String place);

    PoiDTO createPoi(PoiCreationRequest poiCreationRequest);

    PoiDTO getPoiById(Long poiId);

    void deletePoi(Long poiId);

    PoiDTO updatePoi(Long poiId, PoiUpdateRequest request);

    List<PoiDTO> getPoiList(String name, PageRequest pageRequest);
}
