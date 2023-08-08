package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.repository.PoiRepository;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiCrudControllerTest {

    @Mock
    private PoiFacade poiFacade;

    @Mock
    private PoiRepository repository;

    @InjectMocks
    private PoiCrudController poiCrudController;

    private static PoiResponse poiCreationResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiUpdateRequest updateRequest;

    private static PoiEntity entity;


    @BeforeAll
    public static void setUp() {
        poiCreationRequest = getPoiCreationRequest();
        poiCreationResponse = getPoiCreationResponse();
        updateRequest = getPoiUpdateRequest();
        entity = getPoiEntity();

    }

    @Test
    public void createPOI_shouldReturnPoiCreationResponse() {
        when(poiFacade.createPOI(poiCreationRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.createPOI(poiCreationRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void getPOIbyID_shouldReturnPoiResponse() {
        when(poiFacade.getPOIbyID(ID)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.getPOIbyID(ID);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void updatePOI_shouldReturnUpdatedPoiResponse() {
        when(poiFacade.updatePOI(ID,updateRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.updatePOI(ID, updateRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void deletePOI_shouldDeletePOIbyGivenId() {
        poiCrudController.deletePOI(ID);
        verify(poiFacade).deletePOI(ID);
    }
}
