package com.example.TomTomIntegration.messaging.publisher;

import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class RabbitMQPublisherImpl implements RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${poi.logs.exchange}")
    private String poiLogsExchangeName;

    @Value("${poi.logs.routing-key}")
    private String poiLogsRoutingKey;

    @Override
    public void sendPoiLogsMessage(PoiLogMessage poiLogMessage) {
        log.info("Publish [{}] poi message for [{}]", poiLogMessage.getEvent(), poiLogMessage.getPoi().getName());
        rabbitTemplate.convertAndSend(poiLogsExchangeName, poiLogsRoutingKey, poiLogMessage);
    }
}
