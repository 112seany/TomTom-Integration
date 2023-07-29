package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.response.PoiResponse;

public interface POIFacade {

    PoiResponse getPOI(String place);
}
