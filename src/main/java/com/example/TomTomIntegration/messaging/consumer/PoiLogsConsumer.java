package com.example.TomTomIntegration.messaging.consumer;

import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.service.PoiLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableRabbit
@AllArgsConstructor
public class PoiLogsConsumer {

    private final PoiLogService poiLogService;

    @RabbitListener(queues = "${poi.logs.queue}")
    public void process(PoiLogMessage poiLogMessage) {
        log.info("Received [{}] poi message for [{}]", poiLogMessage.getEvent(), poiLogMessage.getPoi().getName());
        poiLogService.createPoiLog(poiLogMessage);
    }
}
