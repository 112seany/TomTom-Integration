package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NearbySearchServiceImpl implements NearbySearchService {

    private final TomTomGateway tomGateway;

    @Override
    public NearbySearchDTO getNearbyPoi(Double latitude, Double longitude) {
        log.info("Search nearby poi lat [{}], lon [{}] ", latitude, longitude);
        return tomGateway.getNearbyPoi(latitude, longitude);
    }
}
