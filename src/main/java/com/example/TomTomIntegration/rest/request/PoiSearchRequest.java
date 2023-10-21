package com.example.TomTomIntegration.rest.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiSearchRequest {

    @Length(min = 3, message = "name length should be not less than 3 characters")
    private String name;

    @Min(value = 0, message = "scoreMin should be not less than 0")
    @Max(value = 5, message = "scoreMin should be not more than 5")
    private Double scoreMin;

    @Min(value = 0, message = "scoreMax should be not less than 0")
    @Max(value = 5, message = "scoreMax should be not more than 5")
    private Double scoreMax;

    @Length(min = 3, message = "country length should be not less than 3 characters")
    private String country;
}
