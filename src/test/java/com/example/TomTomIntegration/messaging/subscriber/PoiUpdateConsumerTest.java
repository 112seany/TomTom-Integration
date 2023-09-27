package com.example.TomTomIntegration.messaging.subscriber;

import com.example.TomTomIntegration.messaging.consumer.PoiUpdateConsumer;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.example.TomTomIntegration.service.PoiLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.TomTomIntegration.helper.TestHelper.getPoiUpdateLogMessage;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PoiUpdateConsumerTest {

    @InjectMocks
    private PoiUpdateConsumer poiUpdateConsumer;

    @Mock
    private PoiLogService poiLogService;

    @Test
    public void testProcessMyQueue() {
        PoiUpdateLogMessage poiUpdateLogMessage = getPoiUpdateLogMessage();

        poiUpdateConsumer.process(poiUpdateLogMessage);

        verify(poiLogService).createPoiLog(poiUpdateLogMessage);
    }
}