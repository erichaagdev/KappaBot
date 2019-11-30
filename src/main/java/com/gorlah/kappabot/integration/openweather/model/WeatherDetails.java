package com.gorlah.kappabot.integration.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {

    @JsonProperty("temp")
    private Double currentTemperature;

    @JsonProperty("pressure")
    private Double pressure;

    @JsonProperty("humidity")
    private Double humidity;

    @JsonProperty("temp_max")
    private Double maximumTemperature;

    @JsonProperty("temp_min")
    private Double minimumTemperature;
}
