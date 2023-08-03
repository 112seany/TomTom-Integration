package com.example.TomTomIntegration.service;


import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.POIMapper;
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
public class POIServiceImplTest {

    private static final String POI = "restaraunt";

    @Mock
    private TomTomGateway tomGateway;

    @Mock
    private POIMapper poiMapper;

    @InjectMocks
    private POIServiceImpl tested;

    private static PoiDTO poiDTO;

    private static PoiResponse poiResponse;

    @BeforeAll
    public static void setUp() {
        poiDTO = getPoiDTO();

        poiResponse = getPoiResponse();
    }

    @Test
    public void getPoiTest_shouldReturnPoiResponse() {
        when(tomGateway.getPOI(POI)).thenReturn(poiDTO);
        when(poiMapper.mapToResponse(poiDTO)).thenReturn(poiResponse);

        PoiResponse actual = tested.getPOI(POI);

        assertEquals(poiResponse, actual);
    }
}