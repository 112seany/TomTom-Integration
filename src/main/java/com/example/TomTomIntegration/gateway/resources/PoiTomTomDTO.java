package com.example.TomTomIntegration.gateway.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiTomTomDTO {

    @JsonProperty(value = "summary")
    private SummaryDTO summaryDTO;

    @JsonProperty(value = "results")
    private List<ResultDTO> resultDTO;
}
