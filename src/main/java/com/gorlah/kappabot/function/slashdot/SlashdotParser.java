package com.gorlah.kappabot.function.slashdot;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SlashdotParser {

    private static final Pattern MOBILE_SLASHDOT = Pattern.compile("https://m\\.slashdot\\.org/story/\\d+");

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36";

    private final LoadingCache<String, String> slashdotTitleCache =
            Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofHours(1))
                    .build(this::getTitle);

    public boolean find(String message) {
        return MOBILE_SLASHDOT.matcher(message).find();
    }

    public List<String> getTitles(String message) {
        return MOBILE_SLASHDOT.matcher(message).results()
                .parallel()
                .map(MatchResult::group)
                .map(slashdotTitleCache::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getTitle(String mobileUrl) {
        String url = mobileUrl.replaceAll("https://m\\.slashdot\\.org/story/", "https://slashdot.org/story/");

        try {
            Document doc = Jsoup.connect(url)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();

            return doc.select(".story-title > a").text();
        } catch (Exception e) {
            log.warn("Failed to parse '" + mobileUrl + "': " + e.getLocalizedMessage());
        }

        return null;
    }
}
