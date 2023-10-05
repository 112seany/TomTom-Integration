package com.example.TomTomIntegration.messaging.message;

import com.example.TomTomIntegration.entity.PoiEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiLogMessage implements Serializable {

    private PoiInfo poi;

    private PoiEvent event;
}