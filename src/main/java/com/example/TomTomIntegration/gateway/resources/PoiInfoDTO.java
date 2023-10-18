package com.example.TomTomIntegration.gateway.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoiInfoDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "phone")
    private String phone;
}
