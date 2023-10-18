package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import com.example.TomTomIntegration.mapper.NearbySearchMapper;
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

    @Mock
    private NearbySearchMapper nearbySearchMapper;

    @InjectMocks
    private NearbySearchFacadeImpl nearbySearchFacade;

    private static NearbySearchResponse nearbySearchResponse;

    private static NearbySearchDTO nearbySearchDTO;

    @BeforeAll
    public static void setUp() {
        nearbySearchResponse = getNearbySearchResponse();
        nearbySearchDTO = getNearbySearchDTO();
    }

    @Test
    public void getNearbyPoi_shouldReturn_NearbySearchResponse() {
        when(nearbySearchService.getNearbyPoi(LAT,LON)).thenReturn(nearbySearchDTO);
        when(nearbySearchMapper.mapToNearbySearchResponse(nearbySearchDTO)).thenReturn(nearbySearchResponse);

        NearbySearchResponse actual = nearbySearchFacade.getNearbyPoi(LAT,LON);

        assertEquals(nearbySearchResponse, actual);
    }
}