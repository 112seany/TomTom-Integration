package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.service.POIService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.POI;
import static com.example.TomTomIntegration.helper.TestHelper.getPoiResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class POIFacadeImplTest {

    @Mock
    private POIService poiService;

    @InjectMocks
    private POIFacadeImpl poiFacade;

    private static PoiResponse poiResponse;

    @BeforeAll
    public static void setUp() {
        poiResponse = getPoiResponse();
    }

    @Test
    public void getPoiTest_shouldReturnPoiResponse() {
        when(poiService.getPOI(POI)).thenReturn(poiResponse);

        PoiResponse actual = poiFacade.getPOI(POI);

        assertEquals(poiResponse, actual);
    }
}