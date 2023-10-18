package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
import com.example.TomTomIntegration.gateway.resources.ResultDTO;
import com.example.TomTomIntegration.rest.response.PoiInfoResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PoiTomTomMapper {

    @Mapping(target = "numberResults", expression = "java(poiTomTomDTO.getSummaryDTO().getNumResults())")
    @Mapping(target = "offset", expression = "java(poiTomTomDTO.getSummaryDTO().getOffset())")
    @Mapping(target = "totalResults", expression = "java(poiTomTomDTO.getSummaryDTO().getTotalResults())")
    @Mapping(target = "poiInfoResponse", expression = "java(mapResultDTOsToPoiInfoResponses(poiTomTomDTO.getResultDTO()))")
    PoiTomTomResponse mapToResponse(PoiTomTomDTO poiTomTomDTO);

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
