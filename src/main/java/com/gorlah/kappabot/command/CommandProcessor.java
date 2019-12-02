package com.gorlah.kappabot.command;


import com.gorlah.kappabot.subcommand.RootCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final RootCommand rootCommand;

    public String process(CommandPayload payload) {
        Command currentSubcommand = rootCommand;
        Command nextSubcommand;

        for (String subcommandString : payload) {
            if (payload.getParameters().isEmpty() && "help".equals(subcommandString)) {
                return currentSubcommand.getDetailedHelpText();
            }

            nextSubcommand = currentSubcommand.getChild(subcommandString);

            if (nextSubcommand == null) {
                payload.addParameter(subcommandString);
            } else {
                currentSubcommand = nextSubcommand;
            }
        }

        if (currentSubcommand.isParent()) {
            return currentSubcommand.getIncorrectUsageText();
        }

        try {
            return currentSubcommand.process(payload);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return currentSubcommand.getErrorText();
        }
    }
}
