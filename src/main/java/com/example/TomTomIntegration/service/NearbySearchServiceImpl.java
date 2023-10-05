package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.NearbySearchMapper;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NearbySearchServiceImpl implements NearbySearchService {

    private final TomTomGateway tomGateway;

    private final NearbySearchMapper nearbySearchMapper;

    @Override
    public NearbySearchResponse getNearbyPoi(Double latitude, Double longitude) {
        NearbySearchDTO nearbySearchDTO = tomGateway.getNearbyPoi(latitude, longitude);

        return nearbySearchMapper.mapToNearbySearchResponse(nearbySearchDTO);
    }
}
