package com.gorlah.kappabot.function.response;

import static com.gorlah.kappabot.util.StandardContentTypes.HTML;
import static com.gorlah.kappabot.util.StandardContentTypes.MARKDOWN;
import static com.gorlah.kappabot.util.StandardContentTypes.PLAIN_TEXT;

public class BotResponses {

    private BotResponses() {}

    public static BotResponse fromMarkdown(String markdown) {
        return ImmutableBotResponse.of(MARKDOWN, markdown);
    }

    public static BotResponse fromPlainText(String text) {
        return ImmutableBotResponse.of(PLAIN_TEXT, text);
    }

    public static BotResponse fromHtml(String html) {
        return ImmutableBotResponse.of(HTML, html);
    }
}
