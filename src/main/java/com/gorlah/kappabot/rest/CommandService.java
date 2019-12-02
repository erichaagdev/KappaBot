package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.command.CommandPayloadBuilder;
import com.gorlah.kappabot.command.CommandProcessor;
import com.gorlah.kappabot.rest.model.CommandRequest;
import com.gorlah.kappabot.rest.model.RestCommandMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Service
@RestController
@RequiredArgsConstructor
public class CommandService {

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    private final CommandProcessor commandProcessor;
    private final CommandPayloadBuilder commandPayloadBuilder;

    @PostMapping("/command/run")
    public String runCommand(@RequestBody CommandRequest request) {
        if (request == null) {
            throw new RuntimeException();
        }
        var command = commandPayloadBuilder.parseMessageAndBuild(new RestCommandMetadata(request));
        return formatResponse(commandProcessor.process(command), request.getUser());
    }

    private String formatResponse(String response, String user) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", user);
        values.put("user.mention", user);

        return StringSubstitutor.replace(response, values);
    }
}
