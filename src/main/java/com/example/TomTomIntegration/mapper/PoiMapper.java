package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoiMapper {

    PoiEntity mapToPoiEntity(PoiCreationRequest request);

    List<PoiDTO> mapToPoiDTOList(List<PoiEntity> entities);

    List<PoiResponse> mapToPoiResponseList(List<PoiDTO> poiDTOList);

    PoiDTO mapToPoiDTO(PoiEntity poiEntity);

    PoiResponse mapToPoiResponse(PoiDTO poiDTO);

    default PoiEntity mapToPoiEntityFromPoiUpdateRequest(PoiEntity entity, PoiUpdateRequest request) {
        return PoiEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .phone(request.getPhone())
                .country(request.getCountry())
                .score(request.getScore())
                .streetName(request.getStreetName())
                .streetNumber(request.getStreetNumber())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }
}
