package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.mapper.PoiUpdateLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
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
    private PoiUpdateLogMapper poiUpdateLogMapper;

    @Mock
    private PoiLogRepository poiLogRepository;

    @InjectMocks
    private PoiLogServiceImpl tested;

    @Test
    public void createPoiLogTest() {
        PoiUpdateLogMessage poiUpdateLogMessage = getPoiUpdateLogMessage();
        PoiLogsEntity poiLogsEntity = getPoiLogsEntity();

        when(poiUpdateLogMapper.mapToPoiLogsEntity(poiUpdateLogMessage)).thenReturn(poiLogsEntity);

        tested.createPoiLog(poiUpdateLogMessage);

        verify(poiLogRepository).save(poiLogsEntity);
    }
}