package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.response.NearbySearchResponse;

public interface NearbySearchFacade {

    NearbySearchResponse getNearbyPoi(Double latitude, Double longitude);
}
