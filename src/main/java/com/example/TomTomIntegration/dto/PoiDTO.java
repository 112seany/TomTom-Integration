package com.example.TomTomIntegration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiDTO {

    private String id;

    private String name;

    private String score;

    private String phone;

    private String streetNumber;

    private String streetName;

    private String country;

    private String latitude;

    private String longitude;
}
