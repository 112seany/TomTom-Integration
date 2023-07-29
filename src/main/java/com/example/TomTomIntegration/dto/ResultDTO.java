package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultDTO {

    @JsonProperty(value = "score")
    private String score;

    @JsonProperty(value = "poi")
    private PoiInfoDTO poi;

    @JsonProperty(value = "address")
    private AddressDTO address;

    @JsonProperty(value = "position")
    private PositionDTO position;

}

