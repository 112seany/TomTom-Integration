package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.helper.TestHelper;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PoiMapperTest {

    private final PoiMapper tested = Mappers.getMapper(PoiMapper.class);

    private static PoiEntity entity;

    private static PoiCreationRequest creationRequest;

    private static PoiUpdateRequest poiUpdateRequest;

    private static PoiResponse poiResponse;

    @BeforeAll
    public static void setUp() {
        entity = getPoiEntity();
        creationRequest = getPoiCreationRequest();
        poiUpdateRequest = getPoiUpdateRequest();
        poiResponse = getPoiCreationResponse();
    }

    @Test
    public void mapToResponse() {
        PoiDTO poiDTO = TestHelper.getPoiDTO();

        PoiTomTomResponse actual = tested.mapToResponse(poiDTO);

        assertEquals(actual.getNumberResults(), poiDTO.getSummaryDTO().getNumResults());
        assertEquals(actual.getOffset(), poiDTO.getSummaryDTO().getOffset());
        assertEquals(actual.getTotalResults(), poiDTO.getSummaryDTO().getTotalResults());
        assertEquals(actual.getPoiInfoResponse().size(), poiDTO.getResultDTO().size());
        assertEquals(actual.getPoiInfoResponse().get(0).getScore(), poiDTO.getResultDTO().get(0).getScore());
        assertEquals(actual.getPoiInfoResponse().get(0).getCountry(), poiDTO.getResultDTO().get(0).getAddress().getCountry());
        assertEquals(actual.getPoiInfoResponse().get(0).getStreetName(), poiDTO.getResultDTO().get(0).getAddress().getStreetName());
        assertEquals(actual.getPoiInfoResponse().get(0).getStreetNumber(), poiDTO.getResultDTO().get(0).getAddress().getStreetNumber());
        assertEquals(actual.getPoiInfoResponse().get(0).getPostalCode(), poiDTO.getResultDTO().get(0).getAddress().getPostalCode());
        assertEquals(actual.getPoiInfoResponse().get(0).getLongitude(), poiDTO.getResultDTO().get(0).getPosition().getLongitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getLatitude(), poiDTO.getResultDTO().get(0).getPosition().getLatitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getLatitude(), poiDTO.getResultDTO().get(0).getPosition().getLatitude());
        assertEquals(actual.getPoiInfoResponse().get(0).getPhone(), poiDTO.getResultDTO().get(0).getPoi().getPhone());
        assertEquals(actual.getPoiInfoResponse().get(0).getName(), poiDTO.getResultDTO().get(0).getPoi().getName());
    }

    @Test
    public void mapToResponse_shouldReturnNullWhenPoiDTOisNull() {
        PoiTomTomResponse actual = tested.mapToResponse(null);

        assertNull(actual);
    }

    @Test
    public void mapToPOIEntity() {
        PoiEntity actual = tested.mapToPoiEntity(creationRequest);

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(entity);
    }

    @Test
    public void mapToPoiEntity_shouldReturnNullWhenCreationRequestIsNull() {
        PoiEntity actual = tested.mapToPoiEntity(null);

        assertNull(actual);
    }

    @Test
    public void mapToPOIEntityFromPoiUpdateRequest() {
        PoiEntity actual = tested.mapToPoiEntityFromPoiUpdateRequest(entity, poiUpdateRequest);

        assertEquals(actual, entity);
    }

    @Test
    public void mapToPoiCreationResponse() {
        PoiResponse actual = tested.mapToPoiCreationResponse(entity);

        assertEquals(actual, poiResponse);
    }

    @Test
    public void mapToPoiCreationResponse_shouldReturnNullWhenEntityIsNull() {
        assertNull(tested.mapToPoiCreationResponse(null));
    }

    @Test
    public void mapToPoiResponseList() {
        List<PoiResponse> actual = tested.mapToPoiResponseList(getPoiEntityList());

        assertEquals(actual, getPoiResponseList());
    }

    @Test
    public void mapToPoiResponseList_shouldReturnNullWhenEntityIsNull() {
        assertNull(tested.mapToPoiResponseList(null));
    }
}