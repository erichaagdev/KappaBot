package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.annotation.KappaBotIntegrationTest;
import com.gorlah.kappabot.rest.CommandServiceTestClient;
import org.junit.jupiter.api.Test;

@KappaBotIntegrationTest
class HelloCommandTest {

    @Test
    void processHello() {
        CommandServiceTestClient.callRunCommand(
                "/kb hello",
                "Hey, Gorlah!");
    }

    @Test
    void processHelloThere() {
        CommandServiceTestClient.callRunCommand(
                "/kb hello there",
                "General Gorlah!");
    }
}
