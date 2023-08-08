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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiServiceImplTest {

    private static final String POI = "restaraunt";

    @Mock
    private TomTomGateway tomGateway;

    @Mock
    private PoiMapper poiMapper;

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

    @BeforeAll
    public static void setUp() {
        poiDTO = getPoiDTO();
        poiResponse = getPoiResponse();

        creationRequest = getPoiCreationRequest();
        creationResponse = getPoiCreationResponse();
        poiEntity = getPoiEntity();
        updateRequest = getPoiUpdateRequest();
    }

    @Test
    public void getPoiTest_shouldReturnPoiResponse() {
        when(tomGateway.getPOI(POI)).thenReturn(poiDTO);
        when(poiMapper.mapToResponse(poiDTO)).thenReturn(poiResponse);

        PoiTomTomResponse actual = tested.getPOI(POI);

        assertEquals(poiResponse, actual);
    }

    @Test
    public void createPOI_shouldReturnPoiCreationResponse() {
        when(poiMapper.mapToPOIEntity(creationRequest)).thenReturn(poiEntity);
        when(poiRepository.save(poiEntity)).thenReturn(poiEntity);
        when(poiMapper.mapToPOICreationResponse(poiEntity)).thenReturn(creationResponse);

        PoiResponse actual = tested.createPOI(creationRequest);

        assertEquals(actual, creationResponse);
        verify(poiRepository).save(poiEntity);
    }

    @Test
    public void getPOIbyID_shouldReturnPoiResponse() {
        when(poiRepository.findById(ID)).thenReturn(Optional.of(poiEntity));
        when(poiMapper.mapToPOICreationResponse(poiEntity)).thenReturn(creationResponse);

        PoiResponse actual = tested.getPOIbyId(ID);

        assertEquals(actual, creationResponse);
    }

    @Test
    public void updatePOI_shouldReturnUpdatedPoiResponse() {
        when(poiRepository.findById(ID)).thenReturn(Optional.of(poiEntity));
        when(poiMapper.mapToPOIEntityFromPoiUpdateRequest(poiEntity, updateRequest)).thenReturn(poiEntity);
        when(poiRepository.save(poiEntity)).thenReturn(poiEntity);
        when(poiMapper.mapToPOICreationResponse(poiEntity)).thenReturn(creationResponse);

        PoiResponse actual = tested.updatePOI(ID, updateRequest);

        assertEquals(actual, creationResponse);
        verify(poiRepository).save(poiEntity);
    }

    @Test
    public void deletePOI_shouldDeletePOIbyGivenId() {
        tested.deletePOI(ID);
        verify(poiRepository).deleteById(ID);
    }
}