package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.NearbySearchFacade;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class NearbySearchControllerTest {

    @Mock
    private NearbySearchFacade nearbySearchFacade;

    @InjectMocks
    private NearbySearchController nearbySearchController;

    private static NearbySearchResponse nearbySearchResponse;

    @BeforeAll
    public static void setUp() {
        nearbySearchResponse = getNearbySearchResponse();
    }

    @Test
    public void getNearbyPoi_shouldReturnNearbySearchResponse() {
        when(nearbySearchFacade.getNearbyPoi(LAT,LON)).thenReturn(nearbySearchResponse);

        ResponseEntity<NearbySearchResponse> actual = nearbySearchController.getNearbyPoi(LAT,LON);

        assertEquals(actual, ResponseEntity.ok(nearbySearchResponse));
    }
}