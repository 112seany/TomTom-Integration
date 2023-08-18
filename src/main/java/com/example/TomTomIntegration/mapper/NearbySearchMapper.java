package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.dto.ResultDTO;
import com.example.TomTomIntegration.rest.response.NearbySearchInfoResponse;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NearbySearchMapper {

    @Mapping(target = "results", expression = "java(mapResultDTOsToNearbySearchInfoResponses(nearbySearchDTO.getResultDTO()))")
    NearbySearchResponse mapToNearbySearchResponse(NearbySearchDTO nearbySearchDTO);

    default List<NearbySearchInfoResponse> mapResultDTOsToNearbySearchInfoResponses(List<ResultDTO> sourceList) {
        return sourceList.stream().map(this::mapResultDTOtoPoiInfoResponse).collect(Collectors.toList());
    }

    default NearbySearchInfoResponse mapResultDTOtoPoiInfoResponse(ResultDTO resultDTO) {
        return NearbySearchInfoResponse.builder()
                .score(resultDTO.getScore())
                .name(resultDTO.getPoi().getName())
                .phone(resultDTO.getPoi().getPhone())
                .country(resultDTO.getAddress().getCountry())
                .freeformAddress(resultDTO.getAddress().getFreeformAddress())
                .countryCode(resultDTO.getAddress().getCountryCode())
                .streetNumber(resultDTO.getAddress().getStreetNumber())
                .streetName(resultDTO.getAddress().getStreetName())
                .postalCode(resultDTO.getAddress().getPostalCode())
                .latitude(resultDTO.getPosition().getLatitude())
                .longitude(resultDTO.getPosition().getLongitude())
                .build();
    }
}
