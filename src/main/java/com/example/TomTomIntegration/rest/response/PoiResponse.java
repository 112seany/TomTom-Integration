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
public class PoiResponse {

    @Schema(example = "1")
    private String id;

    @Schema(example = "Restaurant Classic")
    private String name;

    @Schema(example = "4.4041111279")
    private String score;

    @Schema(example = "+1 503-223-2180")
    private String phone;

    @Schema(example = "24")
    private String streetNumber;

    @Schema(example = "Bogoyavlensk Street")
    private String streetName;

    @Schema(example = "Ukraine")
    private String country;

    @Schema(example = "45.521598")
    private String latitude;

    @Schema(example = "122.42351")
    private String longitude;
}
