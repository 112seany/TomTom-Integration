package com.example.TomTomIntegration.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiUpdateRequest {

    @NotBlank(message = "name should not be null")
    @Schema(example = "Restaurant Classic")
    private String name;

    @NotBlank(message = "score should not be null")
    @Schema(example = "4.4041111279")
    private String score;

    @NotBlank(message = "phone should not be null")
    @Schema(example = "+1 503-223-2180")
    private String phone;

    @NotBlank(message = "streetNumber should not be null")
    @Schema(example = "24")
    private String streetNumber;

    @NotBlank(message = "streetName should not be null")
    @Schema(example = "Bogoyavlensk Street")
    private String streetName;

    @NotBlank(message = "country should not be null")
    @Schema(example = "Ukraine")
    private String country;

    @NotBlank(message = "latitude should not be null")
    @Schema(example = "45.521598")
    private String latitude;

    @NotBlank(message = "longitude should not be null")
    @Schema(example = "122.42351")
    private String longitude;
}
