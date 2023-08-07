package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.dto.ResultDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiInfoResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PoiMapper {

    @Mapping(target = "numberResults", expression = "java(poiDTO.getSummaryDTO().getNumResults())")
    @Mapping(target = "offset", expression = "java(poiDTO.getSummaryDTO().getOffset())")
    @Mapping(target = "totalResults", expression = "java(poiDTO.getSummaryDTO().getTotalResults())")
    @Mapping(target = "poiInfoResponse", expression = "java(mapResultDTOsToPoiInfoResponses(poiDTO.getResultDTO()))")
    PoiTomTomResponse mapToResponse(PoiDTO poiDTO);

    PoiEntity mapToPOIEntity(PoiCreationRequest request);

    PoiResponse mapToPOICreationResponse(PoiEntity entity);

    default List<PoiInfoResponse> mapResultDTOsToPoiInfoResponses(List<ResultDTO> sourceList) {
        return sourceList.stream().map(this::mapResultDTOtoPoiInfoResponse).collect(Collectors.toList());
    }

    default PoiInfoResponse mapResultDTOtoPoiInfoResponse(ResultDTO resultDTO) {
        return PoiInfoResponse.builder()
                .score(resultDTO.getScore())
                .name(resultDTO.getPoi().getName())
                .phone(resultDTO.getPoi().getPhone())
                .country(resultDTO.getAddress().getCountry())
                .streetNumber(resultDTO.getAddress().getStreetNumber())
                .streetName(resultDTO.getAddress().getStreetName())
                .postalCode(resultDTO.getAddress().getPostalCode())
                .latitude(resultDTO.getPosition().getLatitude())
                .longitude(resultDTO.getPosition().getLongitude())
                .build();
    }

    default PoiEntity mapToPOIEntityFromPoiUpdateRequest(PoiEntity entity, PoiUpdateRequest request) {
        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setCountry(request.getCountry());
        entity.setScore(request.getScore());
        entity.setStreetName(request.getStreetName());
        entity.setStreetNumber(request.getStreetNumber());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());

        return entity;
    }
}
