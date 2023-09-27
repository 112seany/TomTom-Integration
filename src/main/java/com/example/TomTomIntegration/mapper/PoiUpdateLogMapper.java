package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.messaging.message.PoiInfo;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoiUpdateLogMapper {

    @Mapping(target = "poi", expression = "java(mapPoiEntityToPoiInfo(poi))")
    PoiUpdateLogMessage mapToPoiUpdateLogMessage(Long poiId, PoiEntity poi);

    default PoiLogsEntity mapToPoiLogsEntity(PoiUpdateLogMessage poiUpdateLogMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return PoiLogsEntity.builder()
                    .poiId(poiUpdateLogMessage.getPoiId())
                    .poi(objectMapper.writeValueAsString(poiUpdateLogMessage.getPoi()))
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
}
