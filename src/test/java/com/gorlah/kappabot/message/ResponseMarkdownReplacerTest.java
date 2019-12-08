package com.gorlah.kappabot.message;

import com.gorlah.kappabot.function.BotRequestEndpoint;
import com.gorlah.kappabot.integration.discord.DiscordMarkdownFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ResponseMarkdownReplacerTest {

    private ResponseMarkdownReplacer markdownReplacer;

    @BeforeEach
    void setup() {
        markdownReplacer = new ResponseMarkdownReplacer(Collections.singleton(new DiscordMarkdownFormatter()),
                new DefaultMarkdownFormatter());
    }

    @Test
    void testReplace() {
        var exampleA = "${markdown.italics.begin}this is italic${markdown.italics.end}";
        var expectedA = "*this is italic*";

        var exampleB = "${markdown.bolditalics.begin}this is bold italics${markdown.bolditalics.end}";
        var expectedB = "***this is bold italics***";

        var exampleC = "${markdown.underline.begin}this is underlined${markdown.underline.end} and " +
                "${markdown.strikethrough.begin}this is strikethrough${markdown.strikethrough.end}";
        var expectedC = "__this is underlined__ and ~~this is strikethrough~~";

        assertEquals(expectedA, markdownReplacer.replace(exampleA, BotRequestEndpoint.DISCORD));
        assertEquals(expectedB, markdownReplacer.replace(exampleB, BotRequestEndpoint.DISCORD));
        assertEquals(expectedC, markdownReplacer.replace(exampleC, BotRequestEndpoint.DISCORD));
    }
}
