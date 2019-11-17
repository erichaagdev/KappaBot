package com.gorlah.kappabot;

import com.gorlah.kappabot.integration.discord.DiscordBot;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class KappaBotApplicationTest {

    @MockBean
    DiscordBot discordBot;

    @Test
    void contextLoads() {
    }
}
