package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.mapper.NearbySearchMapper;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import com.example.TomTomIntegration.service.NearbySearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NearbySearchFacadeImpl implements NearbySearchFacade {

    private final NearbySearchService nearbySearchService;

    private final NearbySearchMapper nearbySearchMapper;

    @Override
    public NearbySearchResponse getNearbyPoi(Double latitude, Double longitude) {
        return nearbySearchMapper.mapToNearbySearchResponse(nearbySearchService.getNearbyPoi(latitude, longitude));
    }
}
