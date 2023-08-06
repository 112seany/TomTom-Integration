package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.repository.PoiRepository;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PoiServiceImpl implements PoiService {

    private final TomTomGateway tomGateway;

    private final PoiMapper poiMapper;

    private final PoiRepository poiRepository;

    @Override
    public PoiResponse getPOI(String place) {
        PoiDTO poiDTO = tomGateway.getPOI(place);

        return poiMapper.mapToResponse(poiDTO);
    }

    @Override
    public PoiCreationResponse createPOI(PoiCreationRequest poiCreationRequest) {
        PoiEntity poiEntity = poiMapper.mapToPOIEntity(poiCreationRequest);
        PoiEntity savedEntity = poiRepository.save(poiEntity);
        return poiMapper.mapToPOICreationResponse(savedEntity);
    }
}