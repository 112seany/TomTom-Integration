package com.example.TomTomIntegration.helper;

import com.example.TomTomIntegration.dto.*;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiInfoResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static final String FREE_FORM_ADDRESS = "200 Southwest Market Street, Portland, OR 97201";
    public static final String COUNTRY_USA = "United States";
    public static final String COUNTRY_CODE = "USA";
    public static final String POSTAL_CODE = "97201";
    public static final String STREET_NUMBER = "200";
    public static final String STREET_NAME = "Southwest Market Street";
    public static final String SCORE = "2.0041568279";
    public static final String QUERY = "restaraunt";
    public static final String QUERY_TYPE = "NON_NEAR";
    public static final String QUERY_TIME = "39";
    public static final String FUZZY_LEVEL = "1";
    public static final String OFFSET = "0";
    public static final String NUM_RESULTS = "10";
    public static final String TOTAL_RESULTS = "27";
    public static final String RESTAURANT_NAME = "Restaraunt Murata";
    public static final String PHONE_NUMBER = "+1 503-227-0080";
    public static final String LATITUDE = "45.511598";
    public static final String LONGITUDE = "-122.678607";
    public static final String POI_URL = "https://api.tomtom.com/search/2/poiSearch/";
    public static final String API_KEY = "3OX3oQRPGeI1PsqyGep38E1CZ6k3n5yY";
    public static final String POI = "restaraunt";
    public static final Long ID = 1L;

    public static PoiTomTomResponse getPoiResponse() {
        return PoiTomTomResponse.builder()
                .poiInfoResponse(getPoiInfoResponse())
                .numberResults(getSummaryDTO().getNumResults())
                .offset(getSummaryDTO().getOffset())
                .totalResults(getSummaryDTO().getTotalResults())
                .build();
    }

    public static SummaryDTO getSummaryDTO() {
        return  SummaryDTO.builder()
                .query(QUERY)
                .queryType(QUERY_TYPE)
                .queryTime(QUERY_TIME)
                .fuzzyLevel(FUZZY_LEVEL)
                .offset(OFFSET)
                .numResults(NUM_RESULTS)
                .totalResults(TOTAL_RESULTS)
                .build();
    }

    public static List<ResultDTO> getResultDTO() {
        return Arrays.asList(ResultDTO.builder()
                .address(getAddressDTO())
                .poi(getPoiInfoDTO())
                .position(getPositionDTO())
                .score(SCORE)
                .build());
    }

    public static URI getUri() {
        return UriComponentsBuilder.fromHttpUrl(POI_URL)
                .path(QUERY + ".json")
                .queryParam("key", API_KEY)
                .build().toUri();
    }

    public static PoiDTO getPoiDTO() {
        return PoiDTO.builder()
                .resultDTO(getResultDTO())
                .summaryDTO(getSummaryDTO())
                .build();
    }

    public static PoiCreationRequest getPoiCreationRequest() {
       return PoiCreationRequest.builder()
               .phone(PHONE_NUMBER)
               .score(SCORE)
               .name(RESTAURANT_NAME)
               .country(COUNTRY_USA)
               .streetNumber(STREET_NUMBER)
               .streetName(STREET_NAME)
               .longitude(LONGITUDE)
               .latitude(LATITUDE)
               .build();
    }

    public static PoiResponse getPoiCreationResponse() {
        return PoiResponse.builder()
                .id(ID.toString())
                .phone(PHONE_NUMBER)
                .score(SCORE)
                .name(RESTAURANT_NAME)
                .country(COUNTRY_USA)
                .streetNumber(STREET_NUMBER)
                .streetName(STREET_NAME)
                .longitude(LONGITUDE)
                .latitude(LATITUDE)
                .build();
    }

    public static PoiEntity getPoiEntity() {
        return PoiEntity.builder()
                .id(ID)
                .phone(PHONE_NUMBER)
                .score(SCORE)
                .name(RESTAURANT_NAME)
                .country(COUNTRY_USA)
                .streetNumber(STREET_NUMBER)
                .streetName(STREET_NAME)
                .longitude(LONGITUDE)
                .latitude(LATITUDE)
                .build();
    }

    public static PoiUpdateRequest getPoiUpdateRequest() {
        return PoiUpdateRequest.builder()
                .phone(PHONE_NUMBER)
                .score(SCORE)
                .name(RESTAURANT_NAME)
                .country(COUNTRY_USA)
                .streetNumber(STREET_NUMBER)
                .streetName(STREET_NAME)
                .longitude(LONGITUDE)
                .latitude(LATITUDE)
                .build();
    }

    private static AddressDTO getAddressDTO() {
          return AddressDTO.builder()
                .freeformAddress(FREE_FORM_ADDRESS)
                .country(COUNTRY_USA)
                .countryCode(COUNTRY_CODE)
                .postalCode(POSTAL_CODE)
                .streetNumber(STREET_NUMBER)
                .streetName(STREET_NAME)
                .build();
    }

    private static PoiInfoDTO getPoiInfoDTO() {
       return PoiInfoDTO.builder()
                .name(RESTAURANT_NAME)
                .phone(PHONE_NUMBER)
                .build();
    }

    private static PositionDTO getPositionDTO() {
        return PositionDTO.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();
    }

    private static List<PoiInfoResponse> getPoiInfoResponse() {
        List<PoiInfoResponse> poiInfoResponseList = new ArrayList<>();
        poiInfoResponseList.add(getPoiInfoResponse(
                ResultDTO.builder()
                .score(SCORE)
                .address(getAddressDTO())
                .position(getPositionDTO())
                .poi(getPoiInfoDTO())
                .build()));

        return poiInfoResponseList;
    }

    private static PoiInfoResponse getPoiInfoResponse(ResultDTO resultDTO) {
        return PoiInfoResponse.builder()
                .score(resultDTO.getScore())
                .name(resultDTO.getPoi().getName())
                .phone(resultDTO.getPoi().getPhone())
                .country(resultDTO.getAddress().getCountry())
                .streetNumber(resultDTO.getAddress().getStreetNumber())
                .streetName(resultDTO.getAddress().getStreetName())
                .postalCode(resultDTO.getAddress().getPostalCode())
                .latitude(resultDTO.getPosition().getLatitude())
                .longitude(resultDTO.getPosition().getLongitude())
                .build();
    }
}
