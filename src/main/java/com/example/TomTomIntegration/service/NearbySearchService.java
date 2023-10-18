package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;

public interface NearbySearchService {

    NearbySearchDTO getNearbyPoi(Double latitude, Double longitude);
}
