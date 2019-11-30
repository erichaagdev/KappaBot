package com.gorlah.kappabot.integration.openweather;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OpenWeatherMessageFormatterTest {

    @Test
    void testFormat() {
        var response = OpenWeatherTestUtil.getExampleResponseA();
        var expected = "Current weather for Nowhere Ville: 22°f cloudy & chance of meatballs (▲33°f ▼11°f)";

        assertEquals(expected, new OpenWeatherMessageFormatter().format(response));
    }
}
