package com.example.TomTomIntegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
