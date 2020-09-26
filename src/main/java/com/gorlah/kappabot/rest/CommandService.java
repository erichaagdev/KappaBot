package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.function.FunctionProcessor;
import com.gorlah.kappabot.function.command.DefaultCommandAdapter;
import com.gorlah.kappabot.rest.model.CommandRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommandService {

    private final FunctionProcessor functionProcessor;

    @PostMapping(value = "/command/run")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(example = "Hey, Gorlah!"))})
    public String runCommand(@RequestBody @Valid CommandRequest request) {
        var author = request.getAuthor();
        request = request.getSource() == null ? request.withSource(DefaultCommandAdapter.DEFAULT_SOURCE) : request;
        return functionProcessor.process(request)
                .stream()
                .map(response -> formatResponse(response, author))
                .collect(Collectors.joining("\n-----------------------\n"));
    }

    private String formatResponse(String response, String author) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", author);
        values.put("user.mention", author);

        return StringSubstitutor.replace(response, values);
    }
}
