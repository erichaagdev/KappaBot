package com.gorlah.kappabot.integration.openweather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenWeatherCityOverrideTest {

    @Mock
    ResourceLoader resourceLoader;

    OpenWeatherCityOverride openWeatherCityOverride;

    @BeforeEach
    void setUp() {
        openWeatherCityOverride = new OpenWeatherCityOverride(resourceLoader);
    }

    @Test
    void testFetchWithOverride() throws IOException {
        var overrideFileContents = """
                # This is an example of openweather-cities.csv
                New Town,55123
                # Far Far Away is a great place to visit!
                Far Far Away,54321
                New New York,10451
                """;
        var resource = mock(Resource.class);
        when(resource.exists()).thenReturn(true);
        when(resource.getInputStream()).thenReturn(new ByteArrayInputStream(overrideFileContents.getBytes(StandardCharsets.UTF_8)));
        when(resourceLoader.getResource("classpath:openweather-cities.csv")).thenReturn(resource);
        openWeatherCityOverride.onStartup();
        assertEquals(Optional.of("54321"), openWeatherCityOverride.getOverrideFor("Far Far Away"));
    }
}
