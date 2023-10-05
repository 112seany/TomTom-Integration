package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.messaging.message.PoiLogMessage;

public interface PoiLogService {

    void createPoiLog(PoiLogMessage message);
}
