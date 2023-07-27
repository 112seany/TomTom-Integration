package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TomTomGatewayImpl implements TomTomGateway {

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getPOI() {
        String curl = "https://api.tomtom.com/search/2/poiSearch/restaraunt.json?key=7OX7oQRPGeI9PsqyGep38E1CZ6k2n2yH";
        ResponseEntity<ResultDTO> response = restTemplate.getForEntity(curl, ResultDTO.class);
        String test = response.getBody().toString();
        System.out.println(test);
        return apiKey;

    }
}
