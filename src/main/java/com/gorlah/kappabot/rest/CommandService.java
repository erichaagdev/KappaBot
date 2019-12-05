package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.function.FunctionProcessor;
import com.gorlah.kappabot.rest.model.CommandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Service
@RestController
@RequiredArgsConstructor
public class CommandService {

    private final FunctionProcessor functionProcessor;

    @PostMapping("/command/run")
    public String runCommand(@RequestBody CommandRequest request) {
        return functionProcessor.process(Objects.requireNonNull(request));
    }
}
