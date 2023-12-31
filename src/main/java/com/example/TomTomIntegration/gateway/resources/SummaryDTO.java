package com.example.TomTomIntegration.gateway.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryDTO {

    @JsonProperty(value = "query")
    private String query;

    @JsonProperty(value = "queryType")
    private String queryType;

    @JsonProperty(value = "queryTime")
    private String queryTime;

    @JsonProperty(value = "numResults")
    private String numResults;

    @JsonProperty(value = "offset")
    private String offset;

    @JsonProperty(value = "totalResults")
    private String totalResults;

    @JsonProperty(value = "fuzzyLevel")
    private String fuzzyLevel;
}
