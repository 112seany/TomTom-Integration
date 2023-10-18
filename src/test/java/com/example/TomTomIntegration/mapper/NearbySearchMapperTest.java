package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NearbySearchMapperTest {

    private final NearbySearchMapper mapper = Mappers.getMapper(NearbySearchMapper.class);

    private static NearbySearchDTO nearbySearchDTO;


    @BeforeAll
    public static void setUp() {
        nearbySearchDTO = getNearbySearchDTO();
    }

    @Test
    public void mapToNearbySearchResponse() {
        NearbySearchResponse actual = mapper.mapToNearbySearchResponse(nearbySearchDTO);

        assertEquals(actual.getResults().get(0).getName(), nearbySearchDTO.getResultDTO().get(0).getPoi().getName());
        assertEquals(actual.getResults().get(0).getCountry(), nearbySearchDTO.getResultDTO().get(0).getAddress().getCountry());
        assertEquals(actual.getResults().get(0).getPostalCode(), nearbySearchDTO.getResultDTO().get(0).getAddress().getPostalCode());
        assertEquals(actual.getResults().get(0).getCountryCode(), nearbySearchDTO.getResultDTO().get(0).getAddress().getCountryCode());
        assertEquals(actual.getResults().get(0).getStreetName(), nearbySearchDTO.getResultDTO().get(0).getAddress().getStreetName());
        assertEquals(actual.getResults().get(0).getStreetNumber(), nearbySearchDTO.getResultDTO().get(0).getAddress().getStreetNumber());
        assertEquals(actual.getResults().get(0).getFreeformAddress(), nearbySearchDTO.getResultDTO().get(0).getAddress().getFreeformAddress());
        assertEquals(actual.getResults().get(0).getPostalCode(), nearbySearchDTO.getResultDTO().get(0).getAddress().getPostalCode());
        assertEquals(actual.getResults().get(0).getScore(), nearbySearchDTO.getResultDTO().get(0).getScore());
        assertEquals(actual.getResults().get(0).getLatitude(), nearbySearchDTO.getResultDTO().get(0).getPosition().getLatitude());
        assertEquals(actual.getResults().get(0).getLongitude(), nearbySearchDTO.getResultDTO().get(0).getPosition().getLongitude());
    }

    @Test
    public void mapToNearbySearchResponse_shouldReturnNullWhenPoiDTOisNull() {
        NearbySearchResponse actual = mapper.mapToNearbySearchResponse(null);

        assertNull(actual);
    }
}