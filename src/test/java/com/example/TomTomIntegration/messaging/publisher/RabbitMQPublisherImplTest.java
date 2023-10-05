package com.example.TomTomIntegration.messaging.publisher;

import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static com.example.TomTomIntegration.helper.TestHelper.getPoiUpdateLogMessage;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RabbitMQPublisherImplTest {

    private static final String POI_LOGS_EXCHANGE_NAME = "poiLogsExchangeName";
    private static final String POI_LOGS_EXCHANGE = "exchange";
    private static final String POI_LOGS_ROUTING_KEY_NAME = "poiLogsRoutingKey";
    private static final String POI_LOGS_ROUTING_KEY = "routing-key";

    private RabbitMQPublisher rabbitMQPublisher;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rabbitMQPublisher = new RabbitMQPublisherImpl(rabbitTemplate);

        ReflectionTestUtils.setField(rabbitMQPublisher, POI_LOGS_EXCHANGE_NAME, POI_LOGS_EXCHANGE);
        ReflectionTestUtils.setField(rabbitMQPublisher, POI_LOGS_ROUTING_KEY_NAME, POI_LOGS_ROUTING_KEY);
    }

    @Test
    public void testSendMessage() {
        PoiLogMessage poiLogMessage = getPoiUpdateLogMessage();

        rabbitMQPublisher.sendPoiLogsMessage(poiLogMessage);

        verify(rabbitTemplate).convertAndSend(POI_LOGS_EXCHANGE, POI_LOGS_ROUTING_KEY, poiLogMessage);
    }
}