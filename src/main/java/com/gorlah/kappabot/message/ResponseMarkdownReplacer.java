package com.gorlah.kappabot.message;

import com.gorlah.kappabot.function.BotRequestEndpoint;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static com.gorlah.kappabot.message.MarkdownVariables.*;
import static java.util.stream.Collectors.toMap;

@Component
public class ResponseMarkdownReplacer {

    private final Map<BotRequestEndpoint, Map<String, String>> replacerMaps;
    private final DefaultMarkdownFormatter defaultMarkdownFormatter;

    public ResponseMarkdownReplacer(Set<EndpointMarkdownFormatter> formatters,
                                    DefaultMarkdownFormatter defaultMarkdownFormatter) {
        this.replacerMaps = formatters.stream()
                .collect(toMap(EndpointMarkdownFormatter::getFormatterFor,
                        MarkdownFormatter::getFormatterMap));
        this.defaultMarkdownFormatter = defaultMarkdownFormatter;
    }

    public String replace(String response, BotRequestEndpoint endpoint) {
        return StringSubstitutor.replace(response, getMapOrDefault(endpoint), PREFIX, SUFFIX);
    }

    private Map<String, String> getMapOrDefault(BotRequestEndpoint endpoint) {
        return replacerMaps.getOrDefault(endpoint, defaultMarkdownFormatter.getFormatterMap());
    }
}
