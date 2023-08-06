package com.example.TomTomIntegration.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI PoiOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("POI")
                .description("POI Api")
                .version("1.0"));
    }
}