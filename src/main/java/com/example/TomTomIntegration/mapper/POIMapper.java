package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.response.PoiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface POIMapper {
    @Mapping(target = "numberResults", expression = "java(poiDTO.getSummaryDTO().getNumResults())")
    @Mapping(target = "offset", expression = "java(poiDTO.getSummaryDTO().getOffset())")
    @Mapping(target = "totalResults", expression = "java(poiDTO.getSummaryDTO().getTotalResults())")
    PoiResponse mapToResponse(PoiDTO poiDTO);
}
