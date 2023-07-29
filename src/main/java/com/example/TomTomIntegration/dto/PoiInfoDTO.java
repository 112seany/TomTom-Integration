package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PoiInfoDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "phone")
    private String phone;
}
