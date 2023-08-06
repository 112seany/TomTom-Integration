package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.service.PoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiFacadeImplTest {

    @Mock
    private PoiService poiService;

    @InjectMocks
    private PoiFacadeImpl poiFacade;

    private static PoiResponse poiResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiCreationResponse poiCreationResponse;

    @BeforeAll
    public static void setUp() {
        poiResponse = getPoiResponse();
        poiCreationRequest = getPoiCreationRequest();
        poiCreationResponse = getPoiCreationResponse();
    }

    @Test
    public void getPOITest_shouldReturnPoiResponse() {
        when(poiService.getPOI(POI)).thenReturn(poiResponse);

        PoiResponse actual = poiFacade.getPOI(POI);

        assertEquals(poiResponse, actual);
    }

    @Test
    public void createPOI_shouldReturnPOICreationResponse() {
        when(poiService.createPOI(poiCreationRequest)).thenReturn(poiCreationResponse);

        PoiCreationResponse actual = poiFacade.createPOI(poiCreationRequest);

        assertEquals(actual, poiCreationResponse);
    }
}