package com.example.TomTomIntegration.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbySearchInfoResponse {

    private String name;

    private String score;

    private String phone;

    private String streetNumber;

    private String streetName;

    private String postalCode;

    private String country;

    private String latitude;

    private String longitude;

    private String freeformAddress;

    private String countryCode;
}
