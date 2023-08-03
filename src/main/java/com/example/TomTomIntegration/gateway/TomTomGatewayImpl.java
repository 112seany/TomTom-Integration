package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.PoiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TomTomGatewayImpl implements TomTomGateway {

    private static final String GET_POI_URL = "https://api.tomtom.com/search/2/poiSearch/";

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PoiDTO getPOI(String place) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(GET_POI_URL)
                .path(place + ".json")
                .queryParam("key", apiKey)
                .build();

        ResponseEntity<PoiDTO> response = restTemplate.getForEntity(uriComponents.toUri(), PoiDTO.class);

        return response.getBody();
    }
}
