package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.NearbySearchDTO;
import com.example.TomTomIntegration.dto.PoiDTO;

public interface TomTomGateway {

    PoiDTO getPOI(String place);

    NearbySearchDTO getNearbyPOI(Double latitude, Double longitude);
}
