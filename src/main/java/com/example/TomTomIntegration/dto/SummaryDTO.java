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

    @Override
    public String toString() {
        return "SummaryDTO{" +
                "query='" + query + '\'' +
                ", queryType='" + queryType + '\'' +
                ", queryTime='" + queryTime + '\'' +
                ", numResults='" + numResults + '\'' +
                ", offset='" + offset + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", fuzzyLevel='" + fuzzyLevel + '\'' +
                '}';
    }
}
