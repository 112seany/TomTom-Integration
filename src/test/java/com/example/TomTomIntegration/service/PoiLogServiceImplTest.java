package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.repository.PoiLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.getPoiLogsEntity;
import static com.example.TomTomIntegration.helper.TestHelper.getPoiUpdateLogMessage;
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

        when(poiLogMapper.mapToPoiLogsEntity(poiLogMessage)).thenReturn(poiLogsEntity);

        tested.createPoiLog(poiLogMessage);

        verify(poiLogRepository).save(poiLogsEntity);
    }
}