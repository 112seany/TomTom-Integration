package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PoiUpdateLogMapperTest {

    private final PoiUpdateLogMapper tested = Mappers.getMapper(PoiUpdateLogMapper.class);

    @Test
    public void mapToPoiUpdateLogMessageTest() {
        PoiEntity entity = getPoiEntity();

        PoiUpdateLogMessage actual = tested.mapToPoiUpdateLogMessage(entity.getId(), entity);

        assertEquals(entity.getId(), actual.getPoiId());
        assertEquals(entity.getName(), actual.getPoi().getName());
        assertEquals(entity.getPhone(), actual.getPoi().getPhone());
        assertEquals(entity.getScore(), actual.getPoi().getScore());
        assertEquals(entity.getCountry(), actual.getPoi().getCountry());
        assertEquals(entity.getStreetName(), actual.getPoi().getStreetName());
        assertEquals(entity.getStreetNumber(), actual.getPoi().getStreetNumber());
        assertEquals(entity.getLatitude(), actual.getPoi().getLatitude());
        assertEquals(entity.getLongitude(), actual.getPoi().getLongitude());
        assertEquals(entity.getId().toString(), actual.getPoi().getId());
    }

    @Test
    public void mapToPoiUpdateLogMessageTest_shouldReturnNullWhenPoiEntityIsNull() {
        PoiUpdateLogMessage actual = tested.mapToPoiUpdateLogMessage(null, null);

        assertNull(actual);
    }

    @Test
    public void mapToPoiLogsEntityTest() {
        PoiUpdateLogMessage poiUpdateLogMessage = getPoiUpdateLogMessage();

        PoiLogsEntity poiLogsEntity = tested.mapToPoiLogsEntity(poiUpdateLogMessage);

        assertNotNull(poiLogsEntity.getTime());
        assertEquals(poiUpdateLogMessage.getPoiId(), poiLogsEntity.getPoiId());
        assertEquals(poiLogsEntity.getPoi(), POI_INFO_CONVERTED_TO_JSON);
    }
}