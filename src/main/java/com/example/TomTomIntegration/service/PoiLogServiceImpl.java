package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.mapper.PoiLogMapper;
import com.example.TomTomIntegration.messaging.message.PoiLogMessage;
import com.example.TomTomIntegration.repository.PoiLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PoiLogServiceImpl implements PoiLogService {

    private final PoiLogMapper poiLogMapper;

    private final PoiLogRepository poiLogRepository;

    @Override
    public void createPoiLog(PoiLogMessage message) {
        log.info("Create poi log with id [{}]", message.getPoi().getId());
        poiLogRepository.save(poiLogMapper.mapToPoiLogsEntity(message));
    }
}
