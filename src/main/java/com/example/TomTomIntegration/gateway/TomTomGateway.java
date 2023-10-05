package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.dto.PoiDTO;

public interface TomTomGateway {

    PoiDTO getPoi(String place);

    NearbySearchDTO getNearbyPoi(Double latitude, Double longitude);
}
