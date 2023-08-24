package com.example.TomTomIntegration.rest.swagger.example;

public class ValidationFailedExample {

    public static final String GET_NEARBY_POI_INVALID_PAYLOAD = "{\n" +
            "    \"message\": \"Validation failed\",\n" +
            "    \"errors\": [\n" +
            "        \"Latitude should be between -90 and 90\",\n" +
            "        \"Longitude should be between -180 and 180\"\n" +
            "    ]\n" +
            "}";
}
