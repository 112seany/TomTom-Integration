package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.rest.response.PoiResponse;

public interface POIService {

    PoiResponse getPOI(String place);
}
