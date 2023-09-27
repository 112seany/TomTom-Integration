package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.exception.DuplicateException;
import com.example.TomTomIntegration.exception.PoiNotFoundException;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.mapper.PoiUpdateLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.example.TomTomIntegration.messaging.publisher.RabbitMQPublisher;
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

    private final RabbitMQPublisher rabbitMQPublisher;

    private final PoiUpdateLogMapper poiUpdateLogMapper;

    @Override
    public PoiTomTomResponse getPOI(String place) {
        PoiDTO poiDTO = tomGateway.getPOI(place);

        return poiMapper.mapToResponse(poiDTO);
    }

    @Override
    public PoiResponse createPOI(PoiCreationRequest poiCreationRequest) {
        checkIsPoiDuplicate(poiCreationRequest.getName());

        PoiEntity poiEntity = poiMapper.mapToPOIEntity(poiCreationRequest);
        PoiEntity savedEntity = poiRepository.save(poiEntity);

        return poiMapper.mapToPOICreationResponse(savedEntity);
    }

    @Override
    public PoiResponse getPOIbyId(Long poiId) {
        PoiEntity entity = checkIfPoiExists(poiId);

        return poiMapper.mapToPOICreationResponse(entity);
    }

    @Override
    public void deletePOI(Long poiId) {
        checkIfPoiExists(poiId);

        poiRepository.deleteById(poiId);
    }

    @Override
    public PoiResponse updatePOI(Long poiId, PoiUpdateRequest request) {
        PoiEntity entity = checkIfPoiExists(poiId);
        PoiEntity entityToSave = poiMapper.mapToPOIEntityFromPoiUpdateRequest(entity, request);

        PoiResponse response = poiMapper.mapToPOICreationResponse(poiRepository.save(entityToSave));

        PoiUpdateLogMessage poiUpdateLogMessage = poiUpdateLogMapper.mapToPoiUpdateLogMessage(poiId, entityToSave);
        rabbitMQPublisher.sendPoiLogsUpdateMessage(poiUpdateLogMessage);

        return response;
    }

    private PoiEntity checkIfPoiExists(Long poiId) {
        return poiRepository.findById(poiId)
                .orElseThrow(() -> new PoiNotFoundException(poiId));
    }

    private void checkIsPoiDuplicate(String name) {
        Optional.ofNullable(poiRepository.findByName(name)).ifPresent(entity -> {
            throw new DuplicateException(name);
        });
    }
}