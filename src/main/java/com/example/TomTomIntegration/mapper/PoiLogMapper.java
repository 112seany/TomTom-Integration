package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiEvent;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.messaging.message.PoiInfo;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoiLogMapper {

    @Mapping(target = "poi", expression = "java(mapPoiEntityToPoiInfo(poi))")
    PoiLogMessage mapToPoiLogMessage(PoiEntity poi, PoiEvent event);

    default PoiLogsEntity mapToPoiLogsEntity(PoiLogMessage poiLogMessage, PoiEntity poiEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return PoiLogsEntity.builder()
                    .poi(objectMapper.writeValueAsString(poiLogMessage.getPoi()))
                    .event(poiLogMessage.getEvent())
                    .poiEntity(poiEntity)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    default PoiInfo mapPoiEntityToPoiInfo(PoiEntity entity) {
      return PoiInfo.builder()
              .id(entity.getId().toString())
              .name(entity.getName())
              .score(entity.getScore())
              .phone(entity.getPhone())
              .country(entity.getCountry())
              .streetName(entity.getStreetName())
              .streetNumber(entity.getStreetNumber())
              .longitude(entity.getLongitude())
              .latitude(entity.getLatitude())
              .build();
    }

    default PoiEntity mapPoiInfoToPoiEntity(PoiInfo poiInfo) {
        return PoiEntity.builder()
                .id(Long.parseLong(poiInfo.getId()))
                .name(poiInfo.getName())
                .score(poiInfo.getScore())
                .phone(poiInfo.getPhone())
                .country(poiInfo.getCountry())
                .streetName(poiInfo.getStreetName())
                .streetNumber(poiInfo.getStreetNumber())
                .longitude(poiInfo.getLongitude())
                .latitude(poiInfo.getLatitude())
                .build();
    }
}
