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
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info("Get poi by name [{}]", place);
        PoiDTO poiDTO = tomGateway.getPoi(place);

        return poiMapper.mapToResponse(poiDTO);
    }

    @Override
    public PoiResponse createPoi(PoiCreationRequest poiCreationRequest) {
        log.info("Create poi [{}]", poiCreationRequest.getName());
        checkIsPoiDuplicate(poiCreationRequest.getName());

        PoiEntity poiEntity = poiMapper.mapToPoiEntity(poiCreationRequest);
        PoiEntity savedEntity = poiRepository.save(poiEntity);
        log.info("Poi with id [{}] was successfully created", savedEntity.getId());

        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(savedEntity, PoiEvent.CREATED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        return poiMapper.mapToPoiCreationResponse(savedEntity);
    }

    @Override
    public PoiResponse getPoiById(Long poiId) {
        log.info("Get poi by id [{}]", poiId);
        PoiEntity entity = checkIfPoiExists(poiId);

        return poiMapper.mapToPoiCreationResponse(entity);
    }

    @Override
    public void deletePoi(Long poiId) {
        log.info("Delete Poi by id [{}]", poiId);
        PoiEntity entityToDelete = checkIfPoiExists(poiId);

        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(entityToDelete, PoiEvent.DELETED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        poiRepository.deleteById(poiId);
    }

    @Override
    public PoiResponse updatePoi(Long poiId, PoiUpdateRequest request) {
        log.info("Update poi by id [{}]", poiId);
        PoiEntity entity = checkIfPoiExists(poiId);
        PoiEntity entityToSave = poiMapper.mapToPoiEntityFromPoiUpdateRequest(entity, request);

        PoiResponse response = poiMapper.mapToPoiCreationResponse(poiRepository.save(entityToSave));

        log.info("Poi with id [{}] was successfully updated", response.getId());
        PoiLogMessage poiLogMessage = poiLogMapper.mapToPoiLogMessage(entityToSave, PoiEvent.UPDATED);
        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        return response;
    }

    @Override
    public List<PoiResponse> getPoiList(String name, PageRequest pageRequest) {
        if (StringUtils.isBlank(name)) {
            log.info("Find all pois by page [{}] and size [{}]", pageRequest.getPageNumber(), pageRequest.getPageSize());
            return poiMapper.mapToPoiResponseList(poiRepository.findAll(pageRequest).getContent());
        } else {
            log.info("Find all pois by name [{}], page [{}] and size [{}]", name, pageRequest.getPageNumber(), pageRequest.getPageSize());
            return poiMapper.mapToPoiResponseList(poiRepository.findByNameContaining(name, pageRequest).getContent());
        }
    }

    private PoiEntity checkIfPoiExists(Long poiId) {
        return poiRepository.findById(poiId)
                .orElseThrow(() -> {
                    log.error("Poi with id [{}] was not found", poiId);
                    throw new PoiNotFoundException(poiId);
                });
    }

    private void checkIsPoiDuplicate(String name) {
        Optional.ofNullable(poiRepository.findByName(name)).ifPresent(entity -> {
            log.error("Poi with name [{}] already exists", name);
            throw new DuplicateException(name);
        });
    }
}