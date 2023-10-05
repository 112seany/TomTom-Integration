package com.example.TomTomIntegration.messaging.publisher;

import com.example.TomTomIntegration.messaging.message.PoiLogMessage;

public interface RabbitMQPublisher {

    void sendPoiLogsMessage(PoiLogMessage poiLogMessage);

}
