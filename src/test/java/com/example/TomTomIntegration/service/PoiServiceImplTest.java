package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
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
import com.example.TomTomIntegration.rest.request.PoiSearchRequest;
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

    private static PoiTomTomDTO poiTomTomDTO;

    private static PoiCreationRequest creationRequest;

    private static PoiUpdateRequest updateRequest;

    private static PoiEntity poiEntity;

    private static PoiLogMessage poiLogMessage;

    private static Page<PoiDTO> poiDTOPage;

    private static Page<PoiEntity> poiEntitiesPage;

    private static PoiSearchRequest poiSearchRequest;


    private static PoiDTO poiDTO;

    @BeforeAll
    public static void setUp() {
        poiTomTomDTO = getPoiTomTomDto();

        creationRequest = getPoiCreationRequest();
        poiEntity = getPoiEntity();
        updateRequest = getPoiUpdateRequest();
        poiDTO = getPoiDto();

        poiLogMessage = getPoiUpdateLogMessage();
        poiEntitiesPage = new PageImpl<>(getPoiEntityList());
        poiDTOPage = new PageImpl<>(getPoiDtoList());
        poiSearchRequest = getPoiSearchRequest();

    }

    @Test
    public void getPoi_shouldReturnPoiResponse() {
        when(tomGateway.getPoi(POI)).thenReturn(poiTomTomDTO);

        PoiTomTomDTO actual = tested.getPoi(POI);

        assertEquals(poiTomTomDTO, actual);
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiMapper.mapToPoiEntity(creationRequest)).thenReturn(poiEntity);
        when(poiRepository.save(poiEntity)).thenReturn(poiEntity);
        when(poiMapper.mapToPoiDTO(poiEntity)).thenReturn(poiDTO);
        when(poiLogMapper.mapToPoiLogMessage(poiEntity, PoiEvent.CREATED)).thenReturn(poiLogMessage);

        PoiDTO actual = tested.createPoi(creationRequest);

        assertEquals(actual, poiDTO);
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
        when(poiMapper.mapToPoiDTO(poiEntity)).thenReturn(poiDTO);

        PoiDTO actual = tested.getPoiById(ID);

        assertEquals(actual, poiDTO);
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
        when(poiMapper.mapToPoiDTO(poiEntity)).thenReturn(poiDTO);
        when(poiLogMapper.mapToPoiLogMessage(poiEntity, PoiEvent.UPDATED)).thenReturn(poiLogMessage);

        PoiDTO actual = tested.updatePoi(ID, updateRequest);

        assertEquals(actual, poiDTO);
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

        tested.deletePoi(ID);

        verify(poiRepository).deleteById(ID);
    }

    @Test
    public void deletePOI_shouldThrowPoiNotFoundException() {
        PoiNotFoundException exception = assertThrows(PoiNotFoundException.class, () -> tested.deletePoi(ID), POI_NOT_FOUND_ERROR_MESSAGE);

        assertEquals(POI_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void getPoiListWhenSearchRequestIsNull_shouldReturnList() {
        when(poiRepository.findAll(PageRequest.of(0, 1))).thenReturn(poiEntitiesPage);
        when(poiMapper.mapToPoiDTOList(poiEntitiesPage.getContent())).thenReturn(poiDTOPage.getContent());

        List<PoiDTO> actual = tested.getPoiList(null, PageRequest.of(0, 1));

        assertEquals(actual, getPoiDtoList());

        verify(poiRepository).findAll(PageRequest.of(0, 1));
        verify(poiMapper).mapToPoiDTOList(poiEntitiesPage.getContent());
        verify(poiRepository, never()).searchPoi(poiSearchRequest.getName(), poiSearchRequest.getScoreMin(), poiSearchRequest.getScoreMax(),
                poiSearchRequest.getCountry(), PageRequest.of(0, 1));
    }

    @Test
    public void getPoiListWhenNameIsNotEmpty_shouldReturnList() {
        when(poiRepository.searchPoi(poiSearchRequest.getName(), poiSearchRequest.getScoreMin(), poiSearchRequest.getScoreMax(),
                poiSearchRequest.getCountry(), PageRequest.of(0, 1))).thenReturn(poiEntitiesPage);

        when(poiMapper.mapToPoiDTOList(poiEntitiesPage.getContent())).thenReturn(poiDTOPage.getContent());

        List<PoiDTO> actual = tested.getPoiList(poiSearchRequest, PageRequest.of(0, 1));

        assertEquals(actual, poiDTOPage.getContent());

        verify(poiRepository).searchPoi(poiSearchRequest.getName(), poiSearchRequest.getScoreMin(), poiSearchRequest.getScoreMax(),
                poiSearchRequest.getCountry(), PageRequest.of(0, 1));
        verify(poiMapper).mapToPoiDTOList(poiEntitiesPage.getContent());
        verify(poiRepository, never()).findAll(PageRequest.of(0, 1));
    }
}