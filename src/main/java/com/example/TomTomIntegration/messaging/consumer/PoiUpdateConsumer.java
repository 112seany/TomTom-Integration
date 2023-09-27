package com.example.TomTomIntegration.messaging.consumer;

import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.example.TomTomIntegration.service.PoiLogService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class PoiUpdateConsumer {

    private final PoiLogService poiLogService;

    @RabbitListener(queues = "${poi.logs.queue}")
    public void process(PoiUpdateLogMessage poiUpdateLogMessage) {
        poiLogService.createPoiLog(poiUpdateLogMessage);
    }
}
