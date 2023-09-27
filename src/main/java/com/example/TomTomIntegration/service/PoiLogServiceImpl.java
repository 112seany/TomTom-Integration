package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.mapper.PoiUpdateLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.example.TomTomIntegration.repository.PoiLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PoiLogServiceImpl implements PoiLogService {

    private final PoiUpdateLogMapper poiUpdateLogMapper;

    private final PoiLogRepository poiLogRepository;

    @Override
    public void createPoiLog(PoiUpdateLogMessage message) {
        poiLogRepository.save(poiUpdateLogMapper.mapToPoiLogsEntity(message));
    }
}
