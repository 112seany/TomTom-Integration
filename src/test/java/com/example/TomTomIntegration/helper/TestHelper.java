package com.example.TomTomIntegration.helper;

import com.example.TomTomIntegration.dto.*;
import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.entity.PoiLogsEntity;
import com.example.TomTomIntegration.messaging.message.PoiInfo;
import com.example.TomTomIntegration.messaging.message.PoiUpdateLogMessage;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Clock;
import java.time.LocalDateTime;
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
    private static final String GET_NEARBY_POI_URL = "https://api.tomtom.com/search/2/nearbySearch/.json";
    public static final String API_KEY = "3OX3oQRPGeI1PsqyGep38E1CZ6k3n5yY";
    public static final String POI = "restaraunt";
    public static final Long ID = 1L;
    public static final Double LON = -122.678607D;
    public static final Double LAT = 45.511598D;
    public static final String POI_INFO_CONVERTED_TO_JSON = "{\"id\":\"1\",\"name\":\"Restaraunt Murata\",\"score\":\"2.0041568279\",\"phone\":\"+1 503-227-0080\",\"streetNumber\":\"200\",\"streetName\":\"Southwest Market Street\",\"country\":\"United States\",\"latitude\":\"45.511598\",\"longitude\":\"-122.678607\"}";

    public static PoiTomTomResponse getPoiResponse() {
        return PoiTomTomResponse.builder()
                .poiInfoResponse(getPoiInfoResponse())
                .numberResults(getSummaryDTO().getNumResults())
                .offset(getSummaryDTO().getOffset())
                .totalResults(getSummaryDTO().getTotalResults())
                .build();
    }

    public static SummaryDTO getSummaryDTO() {
        return SummaryDTO.builder()
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

    public static URI getPoiUri() {
        return UriComponentsBuilder.fromHttpUrl(POI_URL)
                .path(QUERY + ".json")
                .queryParam("key", API_KEY)
                .build().toUri();
    }

    public static URI getNearbySearchUri() {
        return UriComponentsBuilder.fromHttpUrl(GET_NEARBY_POI_URL)
                .queryParam("key", API_KEY)
                .queryParam("lon", LONGITUDE)
                .queryParam("lat", LATITUDE)
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

    public static NearbySearchDTO getNearbySearchDTO() {
        return NearbySearchDTO.builder()
                .resultDTO(getResultDTO())
                .build();
    }

    public static NearbySearchResponse getNearbySearchResponse() {
        return NearbySearchResponse.builder()
                .results(getNearbySearchInfoResponse())
                .build();
    }

    public static PoiUpdateLogMessage getPoiUpdateLogMessage() {
        return PoiUpdateLogMessage.builder()
                .poiId(ID)
                .poi(getPoiInfo())
                .build();
    }

    public static PoiLogsEntity getPoiLogsEntity() {
        return PoiLogsEntity.builder()
                .id(ID)
                .poiId(ID)
                .poi(POI_INFO_CONVERTED_TO_JSON)
                .time(LocalDateTime.now(Clock.systemDefaultZone()))
                .build();
    }

    private static PoiInfo getPoiInfo() {
        return PoiInfo.builder()
                .id(ID.toString())
                .name(RESTAURANT_NAME)
                .score(SCORE)
                .country(COUNTRY_USA)
                .streetName(STREET_NAME)
                .streetNumber(STREET_NUMBER)
                .phone(PHONE_NUMBER)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();
    }

    private static List<NearbySearchInfoResponse> getNearbySearchInfoResponse() {
        return Arrays.asList(NearbySearchInfoResponse.builder()
                .phone(PHONE_NUMBER)
                .name(RESTAURANT_NAME)
                .score(SCORE)
                .countryCode(COUNTRY_CODE)
                .streetName(STREET_NAME)
                .streetNumber(STREET_NUMBER)
                .freeformAddress(FREE_FORM_ADDRESS)
                .postalCode(POSTAL_CODE)
                .longitude(LONGITUDE)
                .latitude(LATITUDE)
                .build());
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
