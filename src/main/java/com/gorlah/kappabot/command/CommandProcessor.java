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

    public String process(CommandPayload command) {
        Command currentSubcommand = rootCommand;
        Command nextSubcommand;

        command.beforeFirst();
        while (command.next()) {
            String subcommandString = command.getSubcommandString();

            if (command.getParameters().isEmpty() && "help".equals(subcommandString)) {
                return currentSubcommand.getDetailedHelpText();
            }

            nextSubcommand = currentSubcommand.getChild(subcommandString);

            if (nextSubcommand == null) {
                command.addParameter(command.getSubcommandString());
            } else {
                currentSubcommand = nextSubcommand;
            }
        }

        if (currentSubcommand.isParent()) {
            return currentSubcommand.getIncorrectUsageText();
        }

        try {
            return currentSubcommand.process(command);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return currentSubcommand.getErrorText();
        }
    }
}
