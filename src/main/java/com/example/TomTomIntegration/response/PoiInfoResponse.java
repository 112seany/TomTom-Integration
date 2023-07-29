package com.example.TomTomIntegration.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoiInfoResponse {

    private String score;

    private String name;

    private String phone;

    private String streetNumber;

    private String streetName;

    private String postalCode;

    private String country;

    private String latitude;

    private String longitude;
}
