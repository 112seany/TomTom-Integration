package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.service.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class POIFacadeImpl implements POIFacade {
    @Autowired
    private POIService poiService;
    @Override
    public String getPOI() {
        return poiService.getPOI();
    }
}
