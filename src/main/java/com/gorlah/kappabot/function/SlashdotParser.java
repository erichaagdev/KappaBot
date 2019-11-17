package com.gorlah.kappabot.function;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SlashdotParser {

    private static final Pattern MOBILE_SLASHDOT = Pattern.compile("https:\\/\\/m\\.slashdot\\.org\\/story\\/\\d+");

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36";

    private SlashdotParser() {

    }

    public static ArrayList<String> getTitles(String message) {
        ArrayList<String> titles = new ArrayList<>();

        Matcher m = MOBILE_SLASHDOT.matcher(message);

        while (m.find()) {
            titles.add(getTitle(m.group()));
        }

        return titles;
    }

    public static String getTitle(String mobileUrl) {
        String url = mobileUrl.replaceAll("https:\\/\\/m\\.slashdot\\.org\\/story\\/", "https://slashdot.org/story/");

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

    public static boolean find(String mobileUrl) {
        return MOBILE_SLASHDOT.matcher(mobileUrl).find();
    }
}
