package com.example.TomTomIntegration.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiCreationRequest {

    @NotBlank(message = "score should not be null")
    private String score;

    @NotBlank(message = "name should not be null")
    private String name;

    @NotBlank(message = "phone should not be null")
    private String phone;

    @NotBlank(message = "streetNumber should not be null")
    private String streetNumber;

    @NotBlank(message = "streetName should not be null")
    private String streetName;

    @NotBlank(message = "country should not be null")
    private String country;

    @NotBlank(message = "latitude should not be null")
    private String latitude;

    @NotBlank(message = "longitude should not be null")
    private String longitude;
}
