package com.gorlah.kappabot.command;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.util.startup.StartupListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
class CommandConfigurer implements StartupListener {

    private final Set<Command> subcommands;

    @Override
    public void onStartup() {
        var classMap = mapByClass(subcommands);

        subcommands.stream()
                .filter(child -> child.getClass() != RootCommand.class)
                .filter(Command::isEnabled)
                .forEach(child -> classMap.get(child.getParent()).addChild(child));

        buildAbsoluteCommandStrings(classMap.get(RootCommand.class));

        log.info("\nRegistered Commands:\n"
                + new SubcommandRegistrationPrinter().print(classMap.get(RootCommand.class)));
    }

    private void buildAbsoluteCommandStrings(Command parent) {
        buildAbsoluteCommandStringsHelper(parent, parent.getName());
    }

    private void buildAbsoluteCommandStringsHelper(Command parent, String absoluteCommandString) {
        parent.setAbsoluteCommandString(absoluteCommandString);

        for (Command child : parent.getChildren()) {
            buildAbsoluteCommandStringsHelper(child,
                    parent.getAbsoluteCommandString() + " " + child.getName());
        }
    }

    private static <T> Map<Class<?>, T> mapByClass(Collection<T> items) {
        return items.stream()
                .collect(ImmutableMap.toImmutableMap(Object::getClass, Function.identity()));
    }

    private static class SubcommandRegistrationPrinter {

        String print(Command subcommand) {
            return print(subcommand, 1);
        }

        private String print(Command subcommand, int depth) {
            var sb =
                    new StringBuilder(Strings.repeat("  ", depth) + "- "
                            + subcommand.getName() + ": " + subcommand.getHelpText() + "\n");
            subcommand.getChildren()
                    .forEach(child -> sb.append(print(child, depth + 1)));
            return sb.toString();
        }
    }
}
