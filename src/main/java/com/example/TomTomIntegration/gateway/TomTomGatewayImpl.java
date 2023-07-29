package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.dto.PoiDTO;
import lombok.AllArgsConstructor;
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

    private static final String API_KEY = "7OX7oQRPGeI9PsqyGep38E1CZ6k2n2yH";

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Autowired
    private  RestTemplate restTemplate;

    @Override
    public PoiDTO getPOI(String place) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(GET_POI_URL)
                .path(place + ".json")
                .queryParam("key", API_KEY)
                .build();

        ResponseEntity<PoiDTO> response = restTemplate.getForEntity(uriComponents.toUri(), PoiDTO.class);

        return response.getBody();
    }
}
