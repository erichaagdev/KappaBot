package com.gorlah.kappabot.subcommand.root.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import com.gorlah.kappabot.subcommand.root.MemeCommand;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeGenerator;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeParameterParser;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeTemplateLoader;
import com.gorlah.kappabot.subcommand.root.meme.util.writer.MemeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MemeTest extends AbstractCommand {

    private final MemeGenerator memeGenerator;
    private final MemeWriter memeFileWriter;
    private final MemeTemplateLoader memeTemplateLoader;
    private final MemeParameterParser memeParameterParser;

    private static final Function<CommandPayload, List<String>> removeFirstParameter =
            payload -> payload.getParameters().subList(1, payload.getParameters().size());

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getHelpText() {
        return "Generates a meme and writes it to a local file.";
    }

    @Override
    public boolean isShownInHelp() {
        return false;
    }

    @Override
    public Class<? extends Command> getParent() {
        return MemeCommand.class;
    }

    @Override
    public String process(CommandPayload payload) {
        return payload.getParameters()
                .stream()
                .findFirst()
                .map(memeName -> writeMemeToFile(memeName, removeFirstParameter.apply(payload)))
                .orElseGet(() -> "I need to know what meme to create. List of known memes: "
                        + String.join(", ", memeTemplateLoader.loadKnownMemes()));
    }

    private String writeMemeToFile(String memeName, List<String> parameters) {
        return memeTemplateLoader.load(memeName)
                .map(template -> writeMemeToFile(template, parameters))
                .orElseGet(() -> "I'm not sure how to create the meme '" + memeName + "'.");
    }

    private String writeMemeToFile(MemeTemplate memeTemplate, List<String> parameters) {
        var memeParameters = memeParameterParser.parseMemeParameters(parameters);
        return memeGenerator.populateTemplate(memeTemplate, memeParameters)
                .map(this::createMeme)
                .orElseGet(() -> "Invalid parameters for meme '" + memeTemplate.getName()
                        + "'. Please provide: " + memeTemplate.getUsage());
    }

    private String createMeme(MemeTemplate template) {
        return memeGenerator.fromTemplate(template)
                .flatMap(memeFileWriter::write)
                .map(response -> "Image successfully written to local file.")
                .orElse("I ran into a problem while writing the meme to a file");
    }
}
