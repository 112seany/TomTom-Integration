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
public class PoiInfoResponse {

    @Schema(example = "2.0041111279")
    private String score;

    @Schema(example = "Restaraunt CaimoN")
    private String name;

    @Schema(example = "+1 503-223-0080")
    private String phone;

    @Schema(example = "200")
    private String streetNumber;

    @Schema(example = "Southwest Market Street")
    private String streetName;

    @Schema(example = "91321")
    private String postalCode;

    @Schema(example = "United States")
    private String country;

    @Schema(example = "45.521598")
    private String latitude;

    @Schema(example = "-122.523607")
    private String longitude;
}
