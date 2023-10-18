package com.example.TomTomIntegration.mapper;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiEvent;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PoiLogMapperTest {

    private final PoiLogMapper tested = Mappers.getMapper(PoiLogMapper.class);

    @Test
    public void mapToPoiLogMessageTest() {
        PoiEntity entity = getPoiEntity();

        PoiLogMessage actual = tested.mapToPoiLogMessage(entity, PoiEvent.UPDATED);

        assertEquals(entity.getName(), actual.getPoi().getName());
        assertEquals(entity.getPhone(), actual.getPoi().getPhone());
        assertEquals(entity.getScore(), actual.getPoi().getScore());
        assertEquals(entity.getCountry(), actual.getPoi().getCountry());
        assertEquals(entity.getStreetName(), actual.getPoi().getStreetName());
        assertEquals(entity.getStreetNumber(), actual.getPoi().getStreetNumber());
        assertEquals(entity.getLatitude(), actual.getPoi().getLatitude());
        assertEquals(entity.getLongitude(), actual.getPoi().getLongitude());
        assertEquals(entity.getId().toString(), actual.getPoi().getId());
        assertEquals(PoiEvent.UPDATED, actual.getEvent());
    }

    @Test
    public void mapToPoiLogMessageTest_shouldReturnNullWhenPoiEntityIsNull() {
        PoiLogMessage actual = tested.mapToPoiLogMessage(null, null);

        assertNull(actual);
    }

    @Test
    public void mapToPoiLogsEntityTest() {
        PoiLogMessage poiLogMessage = getPoiUpdateLogMessage();

        PoiLogsEntity poiLogsEntity = tested.mapToPoiLogsEntity(poiLogMessage);

        assertNotNull(poiLogsEntity.getTime());
        assertEquals(poiLogMessage.getEvent(), poiLogsEntity.getEvent());
        assertEquals(poiLogsEntity.getPoi(), POI_INFO_JSON);
    }
}