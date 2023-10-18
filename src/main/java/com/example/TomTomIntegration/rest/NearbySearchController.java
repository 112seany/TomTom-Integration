package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.NearbySearchFacade;
import com.example.TomTomIntegration.rest.response.NearbySearchResponse;
import com.example.TomTomIntegration.rest.swagger.GetNearbyPoiAPI;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/poi")
public class NearbySearchController {

    private final NearbySearchFacade nearbySearchFacade;

    @GetNearbyPoiAPI
    @GetMapping("/nearby-search")
    public ResponseEntity<NearbySearchResponse> getNearbyPoi(@RequestParam(value = "lat")
                                             @DecimalMin(value = "-90", message = "Latitude should be between -90 and 90")
                                             @DecimalMax(value = "90", message = "Latitude should be between -90 and 90") Double latitude,
                                                            @RequestParam(value = "lon")
                                             @DecimalMin(value = "-180", message = "Longitude should be between -180 and 180")
                                             @DecimalMax(value = "180", message = "Longitude should be between -180 and 180") Double longitude) {

        return ResponseEntity.ok(nearbySearchFacade.getNearbyPoi(latitude, longitude));
    }
}
