package com.gorlah.kappabot.function.slashdot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class MobileSlashdotTitleParser {

    private final Pattern mobileSlashdotArticleUrlPattern = Pattern.compile("https://m\\.slashdot\\.org/story/\\d+");

    private final MobileSlashdotTitleFetcher mobileSlashdotTitleFetcher;

    public List<String> getTitles(String message) {
        return mobileSlashdotArticleUrlPattern.matcher(message).results()
                .map(MatchResult::group)
                .map(mobileSlashdotTitleFetcher::getTitle)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }
}
