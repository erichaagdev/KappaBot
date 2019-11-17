package com.gorlah.kappabot.subcommand;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
class SubcommandConfigurer implements ApplicationListener<ContextRefreshedEvent> {
    
    private final Set<Subcommand> subcommands;
    
    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        var classMap = mapByClass(subcommands);
        subcommands.stream()
                .filter(subcommand -> subcommand.getClass() != RootCommand.class)
                .forEach(subcommand -> classMap.get(subcommand.getParent()).addSubcommand(subcommand));
        log.info("\nRegistered Commands:\n"
                + new SubcommandRegistrationPrinter().print(classMap.get(RootCommand.class)));
    }
    
    private static <T> Map<Class, T> mapByClass(Collection<T> items) {
        return items.stream().collect(ImmutableMap.toImmutableMap(Object::getClass, Function.identity()));
    }
    
    private static class SubcommandRegistrationPrinter {
        
        String print(Subcommand subcommand) {
            return print(subcommand, 1);
        }
        
        private String print(Subcommand subcommand, int depth) {
            var sb =
                    new StringBuilder(Strings.repeat("  ", depth) + "- "
                            + subcommand.getName() + ": " + subcommand.getHelpText() + "\n");
            subcommand.getChildren().values()
                    .forEach(child -> sb.append(print(child, depth + 1)));
            return sb.toString();
        }
    }
}
