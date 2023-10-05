package com.example.TomTomIntegration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poi_logs")
public class PoiLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "poi", columnDefinition = "json")
    private String poi;

    @Builder.Default
    @Column(name = "time", columnDefinition = "timestamp")
    private LocalDateTime time = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "event")
    private PoiEvent event;
}
