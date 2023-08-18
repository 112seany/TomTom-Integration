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

    @Schema(example = "Bright Apartment 1 In The Heart Of Prague")
    private String name;

    @Schema(example = "99.9583358765")
    private String score;

    @Schema(example = "+1 503-223-2180")
    private String phone;

    @Schema(example = "404/7")
    private String streetNumber;

    @Schema(example = "Staropramenná")
    private String streetName;

    @Schema(example = "150 00")
    private String postalCode;

    @Schema(example = "Česko")
    private String country;

    @Schema(example = "50.070162")
    private String latitude;

    @Schema(example = "14.407161")
    private String longitude;

    @Schema(example = "Staropramenná 404/7, 150 00 Praha")
    private String freeformAddress;

    @Schema(example = "CZ")
    private String countryCode;
}
