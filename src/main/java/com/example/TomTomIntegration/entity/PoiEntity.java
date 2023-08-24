package com.example.TomTomIntegration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poi")
public class PoiEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private String score;

    @Column(name = "phone")
    private String phone;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}
