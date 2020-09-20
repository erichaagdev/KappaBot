package com.gorlah.kappabot.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CommandResponse {

    @Schema(example = "Hey, Gorlah!")
    String response;
}
