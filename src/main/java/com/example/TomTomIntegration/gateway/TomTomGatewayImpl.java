package com.example.TomTomIntegration.gateway;

import com.example.TomTomIntegration.gateway.resources.NearbySearchDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
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
    private static final String GET_NEARBY_POI_URL = "https://api.tomtom.com/search/2/nearbySearch/.json";

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PoiTomTomDTO getPoi(String place) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(GET_POI_URL)
                .path(place + ".json")
                .queryParam("key", apiKey)
                .build();

        ResponseEntity<PoiTomTomDTO> response = restTemplate.getForEntity(uriComponents.toUri(), PoiTomTomDTO.class);

        return response.getBody();
    }

    @Override
    public NearbySearchDTO getNearbyPoi(Double latitude, Double longitude) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(GET_NEARBY_POI_URL)
                .queryParam("key", apiKey)
                .queryParam("lon", longitude)
                .queryParam("lat", latitude)
                .build();

        ResponseEntity<NearbySearchDTO> response = restTemplate.getForEntity(uriComponents.toUri(), NearbySearchDTO.class);

        return response.getBody();
    }
}
