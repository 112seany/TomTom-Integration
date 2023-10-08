package com.example.TomTomIntegration.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageablePoiResponse {

    private int page;

    private int size;

    private int numOfResults;

    private List<PoiResponse> pois;
}
