package com.example.TomTomIntegration.response;

import lombok.Data;

import java.util.List;

@Data
public class PoiResponse {

    private String numberResults;

    private String offset;

    private String totalResults;

    private List<PoiInfoResponse> poiInfoResponse;

    @Data
    private static class PoiInfoResponse {

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
}
