package com.example.TomTomIntegration.messaging.publisher;

import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;

public interface RabbitMQPublisher {

    void sendPoiLogsUpdateMessage(PoiUpdateLogMessage poiUpdateLogMessage);
}
