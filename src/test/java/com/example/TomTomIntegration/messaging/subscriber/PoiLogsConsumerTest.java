package com.example.TomTomIntegration.messaging.subscriber;

import com.example.TomTomIntegration.messaging.consumer.PoiLogsConsumer;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.service.PoiLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.getPoiUpdateLogMessage;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PoiLogsConsumerTest {

    @InjectMocks
    private PoiLogsConsumer poiLogsConsumer;

    @Mock
    private PoiLogService poiLogService;

    @Test
    public void testProcessMyQueue() {
        PoiLogMessage poiLogMessage = getPoiUpdateLogMessage();

        poiLogsConsumer.process(poiLogMessage);

        verify(poiLogService).createPoiLog(poiLogMessage);
    }
}