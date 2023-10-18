package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
import com.example.TomTomIntegration.helper.TestHelper;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PoiTomTomMapperTest {

    private final PoiTomTomMapper tested = Mappers.getMapper(PoiTomTomMapper.class);

    @Test
    public void mapToResponse() {
        PoiTomTomDTO poiTomTomDTO = TestHelper.getPoiTomTomDto();

        PoiTomTomResponse actual = tested.mapToResponse(poiTomTomDTO);

        assertEquals(actual.getNumberResults(), poiTomTomDTO.getSummaryDTO().getNumResults());
        assertEquals(actual.getOffset(), poiTomTomDTO.getSummaryDTO().getOffset());
        assertEquals(actual.getTotalResults(), poiTomTomDTO.getSummaryDTO().getTotalResults());
        assertEquals(actual.getPoiInfoResponse().size(), poiTomTomDTO.getResultDTO().size());
        assertEquals(actual.getPoiInfoResponse().get(0).getScore(), poiTomTomDTO.getResultDTO().get(0).getScore());
        assertEquals(actual.getPoiInfoResponse().get(0).getCountry(), poiTomTomDTO.getResultDTO().get(0).getAddress().getCountry());
        assertEquals(actual.getPoiInfoResponse().get(0).getStreetName(), poiTomTomDTO.getResultDTO().get(0).getAddress().getStreetName());
        assertEquals(actual.getPoiInfoResponse().get(0).getStreetNumber(), poiTomTomDTO.getResultDTO().get(0).getAddress().getStreetNumber());
        assertEquals(actual.getPoiInfoResponse().get(0).getPostalCode(), poiTomTomDTO.getResultDTO().get(0).getAddress().getPostalCode());
        assertEquals(actual.getPoiInfoResponse().get(0).getLongitude(), poiTomTomDTO.getResultDTO().get(0).getPosition().getLongitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getLatitude(), poiTomTomDTO.getResultDTO().get(0).getPosition().getLatitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getLatitude(), poiTomTomDTO.getResultDTO().get(0).getPosition().getLatitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getPhone(), poiTomTomDTO.getResultDTO().get(0).getPoi().getPhone());
        assertEquals(actual.getPoiInfoResponse().get(0).getName(), poiTomTomDTO.getResultDTO().get(0).getPoi().getName());
    }

    @Test
    public void mapToResponse_shouldReturnNullWhenPoiDTOisNull() {
        PoiTomTomResponse actual = tested.mapToResponse(null);

        assertNull(actual);
    }
}