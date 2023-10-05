package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.repository.PoiLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PoiLogServiceImpl implements PoiLogService {

    private final PoiLogMapper poiLogMapper;

    private final PoiLogRepository poiLogRepository;

    @Override
    public void createPoiLog(PoiLogMessage message) {
        poiLogRepository.save(poiLogMapper.mapToPoiLogsEntity(message));
    }
}
