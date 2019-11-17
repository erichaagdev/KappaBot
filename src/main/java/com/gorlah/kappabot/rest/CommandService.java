package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandProcessor;
import com.gorlah.kappabot.rest.model.CommandRequest;
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

    @PostMapping("/command/run")
    public String runCommand(@RequestBody CommandRequest request) {
        if (request == null) {
            throw new RuntimeException();
        }
        var command = new Command(commandPrefix, request.getCommand(), request.getUser());
        return formatResponse(commandProcessor.process(command), request.getUser());
    }

    private String formatResponse(String response, String user) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", user);
        values.put("user.mention", user);

        return StringSubstitutor.replace(response, values);
    }
}
