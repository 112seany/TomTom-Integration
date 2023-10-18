package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PoiMapperTest {

    private final PoiMapper tested = Mappers.getMapper(PoiMapper.class);

    private static PoiEntity entity;

    private static PoiCreationRequest creationRequest;

    private static PoiUpdateRequest poiUpdateRequest;

    private static PoiResponse poiResponse;

    private static PoiDTO poiDTO;

    private static List<PoiDTO> poiDTOList;

    @BeforeAll
    public static void setUp() {
        entity = getPoiEntity();
        creationRequest = getPoiCreationRequest();
        poiUpdateRequest = getPoiUpdateRequest();
        poiResponse = getPoiCreationResponse();
        poiDTO = getPoiDto();
        poiDTOList = getPoiDtoList();
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
    public void mapToPoiResponse() {
        PoiResponse actual = tested.mapToPoiResponse(poiDTO);

        assertEquals(actual, poiResponse);
    }

    @Test
    public void mapToPoiResponse_shouldReturnNullWhenDtoIsNull() {
        assertNull(tested.mapToPoiResponse(null));
    }

    @Test
    public void mapToPoiResponseList() {
        List<PoiResponse> actual = tested.mapToPoiResponseList(poiDTOList);

        assertEquals(actual, getPoiResponseList());
    }

    @Test
    public void mapToPoiResponseList_shouldReturnNullWhenEntityIsNull() {
        assertNull(tested.mapToPoiResponseList(null));
    }

    @Test
    public void mapToPoiDTOList() {
        List<PoiDTO> actual = tested.mapToPoiDTOList(List.of(entity));

        assertEquals(actual, poiDTOList);
    }

    @Test
    public void mapToPoiDTOList_shouldReturnEmptyListWhenEntityListIsEmpty() {
        assertTrue(tested.mapToPoiDTOList(List.of()).isEmpty());
    }

    @Test
    public void mapToPoiDTOList_shouldReturnNullWhenEntityListIsNull() {
        assertNull(tested.mapToPoiDTOList(null));
    }

    @Test
    public void mapToPoiDTO() {
        PoiDTO actual = tested.mapToPoiDTO(entity);

        assertEquals(actual, poiDTO);
    }

    @Test
    public void mapToPoiDTO_shouldReturnNullWhenEntityIsNull() {
        assertNull(tested.mapToPoiDTO(null));
    }
}