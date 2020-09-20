package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.function.FunctionProcessor;
import com.gorlah.kappabot.rest.model.CommandRequest;
import com.gorlah.kappabot.rest.model.CommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
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

    @PostMapping("/command/run")
    @Operation()
    public CommandResponse runCommand(@RequestBody @Valid CommandRequest request) {
        var commandResponse = functionProcessor.process(request)
                .stream()
                .map(response -> formatResponse(response, request.getAuthor()))
                .collect(Collectors.joining("\n-----------------------\n"));
        return new CommandResponse(commandResponse);
    }

    private String formatResponse(String response, String author) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", author);
        values.put("user.mention", author);

        return StringSubstitutor.replace(response, values);
    }
}
