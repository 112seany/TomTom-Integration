package com.example.TomTomIntegration.facade;

import org.springframework.stereotype.Component;

@Component
public class POIFacadeImpl implements POIFacade {
    @Override
    public String sayHello() {
        return "Hello";
    }
}
