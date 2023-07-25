package com.example.TomTomIntegration.controller;

import com.example.TomTomIntegration.facade.POIFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class POIController {
    @Autowired
    private POIFacade poiFacade;

    @GetMapping("/poi")
    public String getPOI() {
        return poiFacade.getPOI();
    }
}