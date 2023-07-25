package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.gateway.TomTomGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class POIServiceImpl implements POIService {
    @Autowired
    private TomTomGateway tomGateway;
    @Override
    public String getPOI() {
        return tomGateway.getPOI();
    }
}
