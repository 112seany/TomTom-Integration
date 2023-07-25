package com.example.TomTomIntegration.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class TomTomGatewayImpl implements TomTomGateway {
    @Value("${tomtom.api.key}")
    private String apiKey;

    @Override
    public String getPOI() {
        return apiKey;
    }
}
