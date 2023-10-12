package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.NearbySearchMapper;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NearbySearchServiceImpl implements NearbySearchService {

    private final TomTomGateway tomGateway;

    private final NearbySearchMapper nearbySearchMapper;

    @Override
    public NearbySearchResponse getNearbyPoi(Double latitude, Double longitude) {
        log.info("Search nearby poi lat [{}], lon [{}] ", latitude, longitude);
        NearbySearchDTO nearbySearchDTO = tomGateway.getNearbyPoi(latitude, longitude);

        return nearbySearchMapper.mapToNearbySearchResponse(nearbySearchDTO);
    }
}
