package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDTO {

    @JsonProperty(value = "score")
    private String score;

    @JsonProperty(value = "poi")
    private POI poi;

    @JsonProperty(value = "address")
    private Address address;

    @JsonProperty(value = "position")
    private Position position;


    private static class POI {
        @JsonProperty(value = "name")
        private String name;

        @JsonProperty(value = "phone")
        private String phone;

    }

    private static class Address {
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

    private static class Position {
        @JsonProperty(value = "lat")
        private String latitude;

        @JsonProperty(value = "lon")
        private String longitude;
    }

}

