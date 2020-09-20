package com.gorlah.kappabot.rest.model;

import com.gorlah.kappabot.function.BotRequestMetadata;
import com.gorlah.kappabot.integration.discord.DiscordIntegration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
@AllArgsConstructor
@Schema(example = """
        {
          "author": "Gorlah",
          "message": "hello"
        }
        """)
public class CommandRequest implements BotRequestMetadata {

    @NotEmpty
    @Schema(example = "Gorlah")
    String author;

    @NotEmpty
    @Schema(example = "hello")
    String message;

    @Schema(example = DiscordIntegration.SOURCE)
    String source;
}
