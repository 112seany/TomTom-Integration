package com.example.TomTomIntegration.messaging.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PoiInfo implements Serializable {

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
