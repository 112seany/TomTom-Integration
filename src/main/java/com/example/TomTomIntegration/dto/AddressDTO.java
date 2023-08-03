package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    @JsonProperty(value = "streetNumber")
    private String streetNumber;

    @JsonProperty(value = "streetName")
    private String streetName;

    @JsonProperty(value = "postalCode")
    private String postalCode;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "countryCodeISO3")
    private String countryCode;

    @JsonProperty(value = "freeformAddress")
    private String freeformAddress;
}
