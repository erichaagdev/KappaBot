package com.gorlah.kappabot.function.slashdot;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class MobileSlashdotTitleFetcher {

    private static final String DESKTOP_SLASHDOT_URL = "https://slashdot.org/story/";

    @Value("${slashdot.titleFetcher.userAgent}")
    private final String USER_AGENT;

    private final Pattern MOBILE_SLASHDOT_URL_PATTERN = Pattern.compile("https://m\\.slashdot\\.org/story/");

    private final LoadingCache<String, String> slashdotTitleCache =
            Caffeine.newBuilder()
                    .expireAfterAccess(Duration.ofDays(1))
                    .build(this::getTitleFromWeb);

    public String getTitle(String mobileUrl) {
        return slashdotTitleCache.get(mobileUrl);
    }

    private String getTitleFromWeb(String mobileUrl) {
        try {
            Document doc = Jsoup.connect(replaceMobileUrlWithDesktopUrl(mobileUrl))
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();

            return doc.select(".story-title > a").text();
        } catch (Exception e) {
            log.warn("Failed to parse '" + mobileUrl + "': " + e.getLocalizedMessage());
        }

        return null;
    }

    private String replaceMobileUrlWithDesktopUrl(String mobileUrl) {
        return MOBILE_SLASHDOT_URL_PATTERN.matcher(mobileUrl).replaceAll(DESKTOP_SLASHDOT_URL);
    }
}
