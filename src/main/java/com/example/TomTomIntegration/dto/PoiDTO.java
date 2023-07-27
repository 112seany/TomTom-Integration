package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PoiDTO {

    @JsonProperty(value = "summary")
    private SummaryDTO summaryDTO;

    @JsonProperty(value = "results")
    private List<ResultDTO> resultDTO;
}
