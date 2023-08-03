package com.example.TomTomIntegration.gateway;

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
    private TomTomGatewayImpl tomTomGateway;

    private static URI uri;

    private static PoiDTO poiDTO;

    @BeforeAll
    public static void setUp() {
        uri = getUri();
        poiDTO = PoiDTO.builder()
                .summaryDTO(getSummaryDTO())
                .resultDTO(getResultDTO())
                .build();
    }

    @Test
    public void getPoiTest_shouldReturnPoiDTO() {
        ReflectionTestUtils.setField(tomTomGateway, "apiKey", API_KEY);

        when(restTemplate.getForEntity(uri, PoiDTO.class)).thenReturn(new ResponseEntity<>(poiDTO, HttpStatus.OK));

        PoiDTO actual = tomTomGateway.getPOI(POI);

        assertEquals(poiDTO, actual);
    }
}