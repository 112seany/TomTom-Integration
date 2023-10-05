package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import com.example.TomTomIntegration.service.PoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PoiFacadeImplTest {

    @Mock
    private PoiService poiService;

    @InjectMocks
    private PoiFacadeImpl poiFacade;

    private static PoiTomTomResponse poiResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiResponse poiCreationResponse;

    private static PoiUpdateRequest updateRequest;

    @BeforeAll
    public static void setUp() {
        poiResponse = getPoiResponse();
        poiCreationRequest = getPoiCreationRequest();
        poiCreationResponse = getPoiCreationResponse();
        updateRequest = getPoiUpdateRequest();
    }

    @Test
    public void getPoi_shouldReturnPoiResponse() {
        when(poiService.getPoi(POI)).thenReturn(poiResponse);

        PoiTomTomResponse actual = poiFacade.getPoi(POI);

        assertEquals(poiResponse, actual);
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiService.createPoi(poiCreationRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiFacade.createPoi(poiCreationRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void getPoiById_shouldReturnPoiCreationResponse() {
        when(poiService.getPoiById(ID)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiFacade.getPoiById(ID);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void UpdatePoi_shouldReturnUpdatedPoiResponse() {
        when(poiService.updatePoi(ID, updateRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiFacade.updatePoi(ID, updateRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void deletePoi_shouldDeletePOIbyGivenId() {
        poiFacade.deletePoi(ID);

        verify(poiService).deletePoi(ID);
    }
}