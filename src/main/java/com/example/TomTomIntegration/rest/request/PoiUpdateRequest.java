package com.example.TomTomIntegration.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiUpdateRequest {

    private String name;

    private String score;

    private String phone;

    private String streetNumber;

    private String streetName;

    private String country;

    private String latitude;

    private String longitude;
}
