package com.example.TomTomIntegration.messaging.publisher;

import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class RabbitMQPublisherImpl implements RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${poi.logs.exchange}")
    private String poiLogsExchangeName;

    @Value("${poi.logs.update.routing-key}")
    private String poiLogsRoutingKey;

    @Override
    public void sendPoiLogsUpdateMessage(PoiUpdateLogMessage poiUpdateLogMessage) {
        rabbitTemplate.convertAndSend(poiLogsExchangeName, poiLogsRoutingKey, poiUpdateLogMessage);
    }
}
