package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiControllerTest {

    @Mock
    private PoiFacade poiFacade;

    @InjectMocks
    private PoiController poiController;

    private static PoiTomTomResponse poiResponse;

    @BeforeAll
    public static void setUp() {
        poiResponse = getPoiResponse();
    }

    @Test
    public void getPoi_shouldReturnPoiResponse() {
        when(poiFacade.getPOI(QUERY)).thenReturn(poiResponse);

        PoiTomTomResponse actual = poiController.getPOI(QUERY);

        assertEquals(actual, poiResponse);
    }
}