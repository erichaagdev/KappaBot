package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.annotation.KappaBotIntegrationTest;
import com.gorlah.kappabot.rest.CommandServiceTestClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.startsWith;

@KappaBotIntegrationTest
class RootCommandTest {

    @Test
    void whenCommandIsIssuedUsingPrefixOnly_thenDisplayHelpText() {
        CommandServiceTestClient.callRunCommand("/kb",
                startsWith("Try adding one of the following subcommands to /kb:"));
    }

    @Test
    void whenCommandIsIssuedUsingPrefixAndSpace_thenDisplayHelpText() {
        CommandServiceTestClient.callRunCommand("/kb ",
                startsWith("Try adding one of the following subcommands to /kb:"));
    }
}
