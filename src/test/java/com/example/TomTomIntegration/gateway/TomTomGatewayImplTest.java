package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TomTomGatewayImplTest {

    private static final String API_KEY_NAME = "apiKey";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private static final TomTomGateway tomTomGateway = new TomTomGatewayImpl();

    private static URI uri;

    private static PoiTomTomDTO poiTomTomDTO;

    private static NearbySearchDTO nearbySearchDTO;

    private static URI nearbySearchUri;

    @BeforeAll
    public static void setUp() {
        uri = getPoiUri();
        poiTomTomDTO = PoiTomTomDTO.builder()
                .summaryDTO(getSummaryDTO())
                .resultDTO(getResultDTO())
                .build();

        nearbySearchUri = getNearbySearchUri();
        nearbySearchDTO = getNearbySearchDTO();

        ReflectionTestUtils.setField(tomTomGateway, API_KEY_NAME, API_KEY);
    }

    @Test
    public void getPoi_shouldReturnPoiDTO() {
        when(restTemplate.getForEntity(uri, PoiTomTomDTO.class)).thenReturn(new ResponseEntity<>(poiTomTomDTO, HttpStatus.OK));

        PoiTomTomDTO actual = tomTomGateway.getPoi(POI);

        assertEquals(poiTomTomDTO, actual);
    }

    @Test
    public void getNearbyPoi_shouldReturnNearbySearchDTO() {
        when(restTemplate.getForEntity(nearbySearchUri, NearbySearchDTO.class)).thenReturn(new ResponseEntity<>(nearbySearchDTO, HttpStatus.OK));

        NearbySearchDTO actual = tomTomGateway.getNearbyPoi(LAT, LON);

        assertEquals(actual, nearbySearchDTO);
    }
}