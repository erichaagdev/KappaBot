package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.annotation.KappaBotIntegrationTest;
import com.gorlah.kappabot.rest.CommandServiceClient;
import com.gorlah.kappabot.rest.model.CommandRequest;
import org.junit.jupiter.api.Test;

@KappaBotIntegrationTest
class HelloCommandTest {

    @Test
    void processHello() {
        var request = CommandRequest.builder()
                .author("Gorlah")
                .message("hello")
                .build();

        CommandServiceClient.callRunCommand(request, "Hey, Gorlah!");
    }

    @Test
    void processHelloThere() {
        var request = CommandRequest.builder()
                .author("Gorlah")
                .message("hello there")
                .build();

        CommandServiceClient.callRunCommand(request, "General Gorlah!");
    }
}
