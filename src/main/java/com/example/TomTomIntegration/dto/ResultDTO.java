package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
        @Override
        public String toString() {
            return "POI{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

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

        @Override
        public String toString() {
            return "Address{" +
                    "streetNumber='" + streetNumber + '\'' +
                    ", streetName='" + streetName + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", country='" + country + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", freeformAddress='" + freeformAddress + '\'' +
                    '}';
        }
    }

    private static class Position {
        @Override
        public String toString() {
            return "Position{" +
                    "latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }

        @JsonProperty(value = "lat")
        private String latitude;

        @JsonProperty(value = "lon")
        private String longitude;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "score='" + score + '\'' +
                ", poi=" + poi +
                ", address=" + address +
                ", position=" + position +
                '}';
    }
}

