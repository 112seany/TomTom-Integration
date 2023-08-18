package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.dto.PoiDTO;
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

    private static final String POI = "restaraunt";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private static final TomTomGateway tomTomGateway = new TomTomGatewayImpl();

    private static URI uri;

    private static PoiDTO poiDTO;

    private static NearbySearchDTO nearbySearchDTO;

    private static URI nearbySearchUri;

    @BeforeAll
    public static void setUp() {
        uri = getPoiUri();
        poiDTO = PoiDTO.builder()
                .summaryDTO(getSummaryDTO())
                .resultDTO(getResultDTO())
                .build();

        nearbySearchUri = getNearbySearchUri();
        nearbySearchDTO = getNearbySearchDTO();

        ReflectionTestUtils.setField(tomTomGateway, "apiKey", API_KEY);
    }

    @Test
    public void getPoiTest_shouldReturnPoiDTO() {
        when(restTemplate.getForEntity(uri, PoiDTO.class)).thenReturn(new ResponseEntity<>(poiDTO, HttpStatus.OK));

        PoiDTO actual = tomTomGateway.getPOI(POI);

        assertEquals(poiDTO, actual);
    }

    @Test
    public void getNearbyPOI_shouldReturnNearbySearchDTO() {
        when(restTemplate.getForEntity(nearbySearchUri, NearbySearchDTO.class)).thenReturn(new ResponseEntity<>(nearbySearchDTO, HttpStatus.OK));

        NearbySearchDTO actual = tomTomGateway.getNearbyPOI(LAT, LON);

        assertEquals(actual, nearbySearchDTO);
    }
}