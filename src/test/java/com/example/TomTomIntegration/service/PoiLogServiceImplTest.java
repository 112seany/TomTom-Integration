package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiInfo;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.repository.PoiLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiLogServiceImplTest {

    @Mock
    private PoiLogMapper poiLogMapper;

    @Mock
    private PoiLogRepository poiLogRepository;

    @InjectMocks
    private PoiLogServiceImpl tested;

    @Test
    public void createPoiLog_shouldSaveEntityToDatabase() {
        PoiLogMessage poiLogMessage = getPoiUpdateLogMessage();
        PoiLogsEntity poiLogsEntity = getPoiLogsEntity();
        PoiEntity poiEntity = getPoiEntity();

        when(poiLogMapper.mapPoiInfoToPoiEntity(poiLogMessage.getPoi())).thenReturn(poiEntity);
        when(poiLogMapper.mapToPoiLogsEntity(poiLogMessage, poiEntity)).thenReturn(poiLogsEntity);

        tested.createPoiLog(poiLogMessage);

        verify(poiLogRepository).save(poiLogsEntity);
    }
}