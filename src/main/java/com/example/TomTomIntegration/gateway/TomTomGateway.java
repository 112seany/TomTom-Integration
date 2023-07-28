package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.PoiDTO;

public interface TomTomGateway {

    PoiDTO getPOI(String place);
}
