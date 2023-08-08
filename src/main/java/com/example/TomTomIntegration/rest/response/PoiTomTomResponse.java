package com.example.TomTomIntegration.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoiTomTomResponse {

    @Schema(example = "10")
    private String numberResults;

    @Schema(example = "0")
    private String offset;

    @Schema(example = "27")
    private String totalResults;

    private List<PoiInfoResponse> poiInfoResponse;
}
