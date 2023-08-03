package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

