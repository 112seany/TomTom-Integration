package com.example.TomTomIntegration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class POIController {

    @GetMapping("/users/{userName}")
    public String printUserName(@PathVariable("userName") String name) {
        return "My name is " + name;
    }
}
