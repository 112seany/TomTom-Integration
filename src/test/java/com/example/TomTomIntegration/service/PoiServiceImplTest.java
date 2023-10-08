package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiEvent;
import com.example.TomTomIntegration.exception.DuplicateException;
import com.example.TomTomIntegration.exception.PoiNotFoundException;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.messaging.publisher.RabbitMQPublisher;
import com.example.TomTomIntegration.repository.PoiRepository;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PoiServiceImplTest {

    @Mock
    private TomTomGateway tomGateway;

    @Mock
    private PoiMapper poiMapper;

    @Mock
    private PoiLogMapper poiLogMapper;

    @Mock
    private RabbitMQPublisher rabbitMQPublisher;

    @Mock
    private PoiRepository poiRepository;

    @InjectMocks
    private PoiServiceImpl tested;

    private static PoiDTO poiDTO;

    private static PoiTomTomResponse poiResponse;

    private static PoiResponse creationResponse;

    private static PoiCreationRequest creationRequest;

    private static PoiUpdateRequest updateRequest;

    private static PoiEntity poiEntity;

    private static PoiLogMessage poiLogMessage;

    private static Page<PoiEntity> poiEntitiesPage;

    @BeforeAll
    public static void setUp() {
        poiDTO = getPoiDTO();
        poiResponse = getPoiResponse();

        creationRequest = getPoiCreationRequest();
        creationResponse = getPoiCreationResponse();
        poiEntity = getPoiEntity();
        updateRequest = getPoiUpdateRequest();

        poiLogMessage = getPoiUpdateLogMessage();

        poiEntitiesPage = new PageImpl<>(getPoiEntityList());
    }

    @Test
    public void getPoi_shouldReturnPoiResponse() {
        when(tomGateway.getPoi(POI)).thenReturn(poiDTO);
        when(poiMapper.mapToResponse(poiDTO)).thenReturn(poiResponse);

        PoiTomTomResponse actual = tested.getPoi(POI);

        assertEquals(poiResponse, actual);
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiMapper.mapToPoiEntity(creationRequest)).thenReturn(poiEntity);
        when(poiRepository.save(poiEntity)).thenReturn(poiEntity);
        when(poiMapper.mapToPoiCreationResponse(poiEntity)).thenReturn(creationResponse);
        when(poiLogMapper.mapToPoiLogMessage(poiEntity, PoiEvent.CREATED)).thenReturn(poiLogMessage);

        PoiResponse actual = tested.createPoi(creationRequest);

        assertEquals(actual, creationResponse);
        verify(poiRepository).save(poiEntity);
        verify(poiLogMapper).mapToPoiLogMessage(poiEntity, PoiEvent.CREATED);
        verify(rabbitMQPublisher).sendPoiLogsMessage(poiLogMessage);
    }

    @Test
    public void createPoi_shouldThrowDuplicateException() {
        String expectedErrorMessage = String.format("Poi with name %s already exists.", creationRequest.getName());

        when(poiRepository.findByName(creationRequest.getName())).thenReturn(poiEntity);

        DuplicateException exception = assertThrows(DuplicateException.class, () -> tested.createPoi(creationRequest), expectedErrorMessage);

        assertEquals(expectedErrorMessage, exception.getMessage());

    }

    @Test
    public void getPOIbyID_shouldReturnPoiResponse() {
        when(poiRepository.findById(ID)).thenReturn(Optional.of(poiEntity));
        when(poiMapper.mapToPoiCreationResponse(poiEntity)).thenReturn(creationResponse);

        PoiResponse actual = tested.getPoiById(ID);

        assertEquals(actual, creationResponse);
    }

    @Test
    public void getPoiById_shouldThrowPoiNotFoundException() {
        PoiNotFoundException exception = assertThrows(PoiNotFoundException.class, () -> tested.getPoiById(ID), POI_NOT_FOUND_ERROR_MESSAGE);

        assertEquals(POI_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void updatePoi_shouldReturnUpdatedPoiResponse() {
        when(poiRepository.findById(ID)).thenReturn(Optional.of(poiEntity));
        when(poiMapper.mapToPoiEntityFromPoiUpdateRequest(poiEntity, updateRequest)).thenReturn(poiEntity);
        when(poiRepository.save(poiEntity)).thenReturn(poiEntity);
        when(poiMapper.mapToPoiCreationResponse(poiEntity)).thenReturn(creationResponse);
        when(poiLogMapper.mapToPoiLogMessage(poiEntity, PoiEvent.UPDATED)).thenReturn(poiLogMessage);

        PoiResponse actual = tested.updatePoi(ID, updateRequest);

        assertEquals(actual, creationResponse);
        verify(poiRepository).save(poiEntity);
        verify(poiLogMapper).mapToPoiLogMessage(poiEntity, PoiEvent.UPDATED);
        verify(rabbitMQPublisher).sendPoiLogsMessage(poiLogMessage);
    }

    @Test
    public void updatePoi_shouldThrowPoiNotFoundException() {
        PoiNotFoundException exception = assertThrows(PoiNotFoundException.class, () -> tested.updatePoi(ID, updateRequest), POI_NOT_FOUND_ERROR_MESSAGE);

        assertEquals(POI_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void deletePoi_shouldDeletePOIbyGivenId() {
        when(poiRepository.findById(ID)).thenReturn(Optional.of(poiEntity));

        when(poiLogMapper.mapToPoiLogMessage(poiEntity, PoiEvent.DELETED)).thenReturn(poiLogMessage);

        tested.deletePoi(ID);

        verify(poiLogMapper).mapToPoiLogMessage(poiEntity, PoiEvent.DELETED);
        verify(rabbitMQPublisher).sendPoiLogsMessage(poiLogMessage);
        verify(poiRepository).deleteById(ID);
    }

    @Test
    public void deletePOI_shouldThrowPoiNotFoundException() {
        PoiNotFoundException exception = assertThrows(PoiNotFoundException.class, () -> tested.deletePoi(ID), POI_NOT_FOUND_ERROR_MESSAGE);

        assertEquals(POI_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void getPoiListWhenNameIsEmpty_shouldReturnList() {
        when(poiRepository.findAll(PageRequest.of(0, 1))).thenReturn(poiEntitiesPage);
        when(poiMapper.mapToPoiResponseList(poiEntitiesPage.getContent())).thenReturn(getPoiResponseList());

        List<PoiResponse> actual = tested.getPoiList(StringUtils.EMPTY, PageRequest.of(0,1));

        assertEquals(actual, getPoiResponseList());

        verify(poiRepository, never()).findByNameContaining(StringUtils.EMPTY, PageRequest.of(0,1));
        verify(poiRepository).findAll(PageRequest.of(0,1));
        verify(poiMapper).mapToPoiResponseList(poiEntitiesPage.getContent());
    }

    @Test
    public void getPoiListWhenNameIsNotEmpty_shouldReturnList() {
        when(poiRepository.findByNameContaining(RESTAURANT, PageRequest.of(0, 1))).thenReturn(poiEntitiesPage);
        when(poiMapper.mapToPoiResponseList(poiEntitiesPage.getContent())).thenReturn(getPoiResponseList());

        List<PoiResponse> actual = tested.getPoiList(RESTAURANT, PageRequest.of(0,1));

        assertEquals(actual, getPoiResponseList());

        verify(poiRepository, never()).findAll(PageRequest.of(0,1));
        verify(poiRepository).findByNameContaining(RESTAURANT, PageRequest.of(0,1));
        verify(poiMapper).mapToPoiResponseList(poiEntitiesPage.getContent());
    }
}