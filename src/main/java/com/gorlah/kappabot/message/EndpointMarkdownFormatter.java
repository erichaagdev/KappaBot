package com.gorlah.kappabot.message;

import com.gorlah.kappabot.function.BotRequestEndpoint;

public interface EndpointMarkdownFormatter extends MarkdownFormatter {

    BotRequestEndpoint getFormatterFor();
}
