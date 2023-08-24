package com.example.TomTomIntegration.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiResponse {

    private String id;

    private String name;

    private String score;

    private String phone;

    private String streetNumber;

    private String streetName;

    private String country;

    private String latitude;

    private String longitude;
}
