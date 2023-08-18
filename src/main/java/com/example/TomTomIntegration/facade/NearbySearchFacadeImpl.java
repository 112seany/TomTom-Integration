package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import com.example.TomTomIntegration.service.NearbySearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NearbySearchFacadeImpl implements NearbySearchFacade {

    private final NearbySearchService nearbySearchService;

    @Override
    public NearbySearchResponse getNearbyPOI(Double latitude, Double longitude) {
        return nearbySearchService.getNearbyPOI(latitude, longitude);
    }
}
