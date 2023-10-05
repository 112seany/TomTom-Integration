package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiEvent;
import com.example.TomTomIntegration.exception.DuplicateException;
import com.example.TomTomIntegration.exception.PoiNotFoundException;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.messaging.publisher.RabbitMQPublisher;
import com.example.TomTomIntegration.repository.PoiRepository;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PoiServiceImpl implements PoiService {

    private final TomTomGateway tomGateway;

    private final PoiMapper poiMapper;

    private final PoiRepository poiRepository;

    private final RabbitMQPublisher rabbitMQPublisher;

    private final PoiLogMapper poiLogMapper;

    @Override
    public PoiTomTomResponse getPoi(String place) {
        PoiDTO poiDTO = tomGateway.getPoi(place);

        return poiMapper.mapToResponse(poiDTO);
    }

    @Override
    public PoiResponse createPoi(PoiCreationRequest poiCreationRequest) {
        checkIsPoiDuplicate(poiCreationRequest.getName());

        PoiEntity poiEntity = poiMapper.mapToPOIEntity(poiCreationRequest);
        PoiEntity savedEntity = poiRepository.save(poiEntity);

        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(savedEntity, PoiEvent.CREATED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        return poiMapper.mapToPOICreationResponse(savedEntity);
    }

    @Override
    public PoiResponse getPoiById(Long poiId) {
        PoiEntity entity = checkIfPoiExists(poiId);

        return poiMapper.mapToPOICreationResponse(entity);
    }

    @Override
    public void deletePoi(Long poiId) {
        PoiEntity entityToDelete = checkIfPoiExists(poiId);

        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(entityToDelete, PoiEvent.DELETED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        poiRepository.deleteById(poiId);
    }

    @Override
    public PoiResponse updatePoi(Long poiId, PoiUpdateRequest request) {
        PoiEntity entity = checkIfPoiExists(poiId);
        PoiEntity entityToSave = poiMapper.mapToPOIEntityFromPoiUpdateRequest(entity, request);

        PoiResponse response = poiMapper.mapToPOICreationResponse(poiRepository.save(entityToSave));

        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(entityToSave, PoiEvent.UPDATED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        return response;
    }

    @Override
    public List<PoiResponse> getPoiList(String name) {
        return poiMapper.mapToPoiResponseList(poiRepository.findByNameContaining(name));
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