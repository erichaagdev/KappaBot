package com.gorlah.kappabot.integration.openweather;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.gorlah.kappabot.util.startup.StartupListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
class OpenWeatherCityOverride implements StartupListener {

    private final ResourceLoader resourceLoader;

    @Value("${openweather.override}")
    private String overrideCsvPath = "classpath:openweather-cities.csv";

    private Map<String, String> overrideMap = Map.of();

    @Override
    public void onStartup() {
        initializeOverrideMap();
    }

    private void initializeOverrideMap() {
        var resource = resourceLoader.getResource(overrideCsvPath);
        if (resource.exists()) {
            try (var inputStream = resource.getInputStream()) {
                overrideMap = Stream.of(inputStream.readAllBytes())
                        .map(String::new)
                        .flatMap(Splitter.on('\n')::splitToStream)
                        .filter(row -> !row.startsWith("#") && row.contains(","))
                        .map(Splitter.on(',')::splitToList)
                        .collect(ImmutableMap.toImmutableMap(
                                parts -> parts.get(0).toLowerCase(),
                                parts -> parts.get(1),
                                (a, b) -> b));
                if (!overrideMap.isEmpty()) {
                    log.debug("Loaded {} city overrides", overrideMap.size());
                }
            } catch (IOException | RuntimeException e) {
                log.warn("Failed to load " + overrideCsvPath, e);
            }
        }
    }

    Optional<String> getOverrideFor(String cityName) {
        return Optional.ofNullable(overrideMap.get(cityName.toLowerCase()));
    }
}
