package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.dto.ResultDTO;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.response.PoiCreationResponse;
import com.example.TomTomIntegration.rest.response.PoiInfoResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
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
    PoiResponse mapToResponse(PoiDTO poiDTO);

    PoiEntity mapToPOIEntity(PoiCreationRequest request);

    PoiCreationResponse mapToPOICreationResponse(PoiEntity entity);

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
}
