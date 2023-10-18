package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;

public interface TomTomGateway {

    PoiTomTomDTO getPoi(String place);

    NearbySearchDTO getNearbyPoi(Double latitude, Double longitude);
}
