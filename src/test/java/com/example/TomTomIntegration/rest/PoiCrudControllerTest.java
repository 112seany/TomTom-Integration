package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
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
public class PoiCrudControllerTest {

    @Mock
    private PoiFacade poiFacade;

    @InjectMocks
    private PoiCrudController poiCrudController;

    private static PoiResponse poiCreationResponse;

    private static PoiCreationRequest poiCreationRequest;

    @BeforeAll
    public static void setUp() {
        poiCreationRequest = getPoiCreationRequest();
        poiCreationResponse = getPoiCreationResponse();
    }

    @Test
    public void createPOI_shouldReturnPoiCreationResponse() {
        when(poiFacade.createPOI(poiCreationRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.createPOI(poiCreationRequest);

        assertEquals(actual, poiCreationResponse);
    }
}
