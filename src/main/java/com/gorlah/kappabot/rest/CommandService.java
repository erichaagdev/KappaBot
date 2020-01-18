package com.gorlah.kappabot.rest;

import com.google.common.base.Strings;
import com.gorlah.kappabot.function.FunctionProcessor;
import com.gorlah.kappabot.rest.model.CommandRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
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

    private final FunctionProcessor functionProcessor;

    @PostMapping("/command/run")
    public String runCommand(@RequestBody CommandRequest request) {
        validate(request);
        return formatResponse(functionProcessor.process(request), request.getAuthor());
    }

    private void validate(CommandRequest request) {
        if (request == null
                || Strings.isNullOrEmpty(request.getAuthor())
                || Strings.isNullOrEmpty(request.getMessage())
                || Strings.isNullOrEmpty(request.getSource())) {
            throw new RuntimeException();
        }
    }

    private String formatResponse(String response, String author) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", author);
        values.put("user.mention", author);

        return StringSubstitutor.replace(response, values);
    }
}
