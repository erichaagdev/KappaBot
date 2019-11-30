package com.gorlah.kappabot.integration.openweather;

import com.google.common.collect.ImmutableList;
import com.gorlah.kappabot.integration.openweather.model.OpenWeatherResponse;
import com.gorlah.kappabot.integration.openweather.model.WeatherDescription;
import com.gorlah.kappabot.integration.openweather.model.WeatherDetails;


class OpenWeatherTestUtil {

    private OpenWeatherTestUtil() {}

    static OpenWeatherResponse getExampleResponseA() {
        var details = new WeatherDetails();
        details.setCurrentTemperature(22.22);
        details.setMinimumTemperature(11.11);
        details.setMaximumTemperature(33.33);
        var description1 = new WeatherDescription();
        description1.setDescription("cloudy");
        var description2 = new WeatherDescription();
        description2.setDescription("chance of meatballs");
        var response = new OpenWeatherResponse();
        response.setCity("Nowhere Ville");
        response.setDetails(details);
        response.setDescriptions(ImmutableList.of(description1, description2));
        return response;
    }
}
