package com.example.TomTomIntegration.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiTomTomResponse {

    private String numberResults;

    private String offset;

    private String totalResults;

    private List<PoiInfoResponse> poiInfoResponse;
}
