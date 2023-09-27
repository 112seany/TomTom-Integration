package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;

public interface PoiLogService {

    void createPoiLog(PoiUpdateLogMessage message);
}
