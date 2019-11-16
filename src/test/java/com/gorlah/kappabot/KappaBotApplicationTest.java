package com.gorlah.kappabot;

import com.gorlah.kappabot.bots.discord.DiscordBot;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class KappaBotApplicationTest {

	@MockBean
	DiscordBot discordBot;

	@Test
	void contextLoads() {
	}
}
