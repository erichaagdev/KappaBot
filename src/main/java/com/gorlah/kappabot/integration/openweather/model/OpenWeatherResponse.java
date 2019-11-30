package com.gorlah.kappabot.integration.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {

    @JsonProperty("weather")
    private List<WeatherDescription> descriptions;

    @JsonProperty("main")
    private WeatherDetails details;

    @JsonProperty("name")
    private String city;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cod")
    private Long code;
}
