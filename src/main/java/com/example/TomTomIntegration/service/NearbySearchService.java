package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.rest.response.NearbySearchResponse;

public interface NearbySearchService {

    NearbySearchResponse getNearbyPoi(Double latitude, Double longitude);
}
