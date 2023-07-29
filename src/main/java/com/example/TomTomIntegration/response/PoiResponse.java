package com.example.TomTomIntegration.response;

import lombok.Data;

import java.util.List;

@Data
public class PoiResponse {

    private String numberResults;

    private String offset;

    private String totalResults;

    private List<PoiInfoResponse> poiInfoResponse;

}
