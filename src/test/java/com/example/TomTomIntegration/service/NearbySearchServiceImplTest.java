package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
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
public class NearbySearchServiceImplTest {

    @InjectMocks
    private NearbySearchServiceImpl nearbySearchService;

    @Mock
    private TomTomGateway tomGateway;

    private static NearbySearchDTO nearbySearchDTO;

    @BeforeAll
    public static void setUp() {
        nearbySearchDTO = getNearbySearchDTO();
    }

    @Test
    public void getNearbyPOI_ShouldReturnNearbySearchResponse() {
        when(tomGateway.getNearbyPoi(LAT, LON)).thenReturn(nearbySearchDTO);

        NearbySearchDTO actual = nearbySearchService.getNearbyPoi(LAT, LON);

        assertEquals(actual, nearbySearchDTO);
    }
}