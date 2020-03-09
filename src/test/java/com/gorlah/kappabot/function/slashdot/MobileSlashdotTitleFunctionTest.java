package com.gorlah.kappabot.function.slashdot;

import com.gorlah.kappabot.function.response.BotResponses;
import com.gorlah.kappabot.integration.ImmutableBotRequestMetadata;
import com.gorlah.kappabot.integration.discord.DiscordIntegration;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class MobileSlashdotTitleFunctionTest {

    @Mock MobileSlashdotTitleParser parser;

    MobileSlashdotTitleFunction function;

    @BeforeEach
    void setUp() {
        function = new MobileSlashdotTitleFunction(parser);
    }

    @Test
    void process() {
        var mobileSlashdotUrl = "https://m.slashdot.org/story/123456";
        var mobileSlashdotTitle = "KappaBot Declared Bot Of The Year";

        when(parser.containsMobileSlashdotUrl(mobileSlashdotUrl)).thenReturn(true);
        when(parser.getTitles(mobileSlashdotUrl)).thenReturn(List.of(mobileSlashdotTitle));

        var request = ImmutableBotRequestMetadata.builder()
                .author("KappaBot")
                .message(mobileSlashdotUrl)
                .source(DiscordIntegration.SOURCE)
                .build();

        var result = function.process(request);
        var expected = Optional.of(BotResponses.fromMarkdown("> " + mobileSlashdotTitle));

        assertEquals(result, expected);
    }
}