package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.repository.PoiRepository;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PoiServiceImpl implements PoiService {

    private final TomTomGateway tomGateway;

    private final PoiMapper poiMapper;

    private final PoiRepository poiRepository;

    @Override
    public PoiTomTomResponse getPOI(String place) {
        PoiDTO poiDTO = tomGateway.getPOI(place);

        return poiMapper.mapToResponse(poiDTO);
    }

    @Override
    public PoiResponse createPOI(PoiCreationRequest poiCreationRequest) {
        PoiEntity poiEntity = poiMapper.mapToPOIEntity(poiCreationRequest);
        PoiEntity savedEntity = poiRepository.save(poiEntity);

        return poiMapper.mapToPOICreationResponse(savedEntity);
    }

    @Override
    public PoiResponse getPOIbyId(Long poiId) {
        Optional<PoiEntity> optionalEntity = poiRepository.findById(poiId);
        PoiEntity entity = optionalEntity.get();

        return poiMapper.mapToPOICreationResponse(entity);
    }

    @Override
    public void deletePOI(Long poiId) {
        poiRepository.deleteById(poiId);
    }

    @Override
    public PoiResponse updatePOI(Long poiId, PoiUpdateRequest request) {
        PoiEntity entity = poiRepository.findById(poiId).get();
        PoiEntity entityToSave = poiMapper.mapToPOIEntityFromPoiUpdateRequest(entity, request);

       return poiMapper.mapToPOICreationResponse(poiRepository.save(entityToSave));

    }
}