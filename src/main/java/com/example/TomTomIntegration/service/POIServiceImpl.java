package com.example.TomTomIntegration.service;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.gateway.TomTomGateway;
import com.example.TomTomIntegration.mapper.POIMapper;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class POIServiceImpl implements POIService {

    private final TomTomGateway tomGateway;

    private final POIMapper poiMapper;

    @Override
    public PoiResponse getPOI(String place) {
        PoiDTO poiDTO = tomGateway.getPOI(place);

        return poiMapper.mapToResponse(poiDTO);
    }
}