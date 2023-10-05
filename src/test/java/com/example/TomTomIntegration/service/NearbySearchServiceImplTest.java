package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.NearbySearchMapper;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
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
public class NearbySearchServiceImplTest {

    @InjectMocks
    private NearbySearchServiceImpl nearbySearchService;

    @Mock
    private TomTomGateway tomGateway;

    @Mock
    private NearbySearchMapper nearbySearchMapper;

    private static NearbySearchDTO nearbySearchDTO;

    private static NearbySearchResponse nearbySearchResponse;

    @BeforeAll
    public static void setUp() {
        nearbySearchDTO = getNearbySearchDTO();
        nearbySearchResponse = getNearbySearchResponse();
    }

    @Test
    public void getNearbyPOI_ShouldReturnNearbySearchResponse() {
        when(tomGateway.getNearbyPoi(LAT, LON)).thenReturn(nearbySearchDTO);
        when(nearbySearchMapper.mapToNearbySearchResponse(nearbySearchDTO)).thenReturn(nearbySearchResponse);

        NearbySearchResponse actual = nearbySearchService.getNearbyPoi(LAT, LON);

        assertEquals(actual, nearbySearchResponse);
    }
}