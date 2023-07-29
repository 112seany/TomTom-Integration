package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PositionDTO {

    @JsonProperty(value = "lat")
    private String latitude;

    @JsonProperty(value = "lon")
    private String longitude;
}
