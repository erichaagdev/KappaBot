package com.gorlah.kappabot.integration.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDescription {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("main")
    private String descriptionName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String iconId;
}
