package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import com.example.TomTomIntegration.service.NearbySearchService;
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
public class NearbySearchFacadeImplTest {

    @Mock
    private NearbySearchService nearbySearchService;

    @InjectMocks
    private NearbySearchFacadeImpl nearbySearchFacade;

    private static NearbySearchResponse nearbySearchResponse;

    @BeforeAll
    public static void setUp() {
        nearbySearchResponse = getNearbySearchResponse();
    }

    @Test
    public void getNearbyPOI_shouldReturn_NearbySearchResponse() {
        when(nearbySearchService.getNearbyPOI(LAT,LON)).thenReturn(nearbySearchResponse);

        NearbySearchResponse actual = nearbySearchFacade.getNearbyPOI(LAT,LON);

        assertEquals(nearbySearchResponse, actual);
    }
}