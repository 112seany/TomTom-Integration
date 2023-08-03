package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.response.PoiResponse;

public interface POIFacade {

    PoiResponse getPOI(String place);
}
