package com.gorlah.kappabot.subcommand.root.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.integration.imgur.ImgurIntegration;
import com.gorlah.kappabot.jpa.entity.SavedMeme;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import com.gorlah.kappabot.subcommand.root.MemeCommand;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeGenerator;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeParameterParser;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeTemplateLoader;
import com.gorlah.kappabot.subcommand.root.meme.util.SavedMemeService;
import com.gorlah.kappabot.subcommand.root.meme.util.writer.MemeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MemeMe extends AbstractCommand {

    private final ImgurIntegration imgurIntegration;

    private final MemeGenerator memeGenerator;
    private final MemeParameterParser memeParameterParser;
    private final MemeTemplateLoader memeTemplateLoader;
    private final MemeWriter memeImgurWriter;
    private final SavedMemeService savedMemeService;

    private static final Function<CommandPayload, List<String>> removeFirstParameter =
            payload -> payload.getParameters().subList(1, payload.getParameters().size());

    @Override
    public String getName() {
        return "me";
    }

    @Override
    public String getHelpText() {
        return "Generates a meme.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return MemeCommand.class;
    }

    @Override
    public boolean isEnabled() {
        return imgurIntegration.isEnabled();
    }

    @Override
    public String process(CommandPayload payload) {
        return payload.getParameters()
                .stream()
                .findFirst()
                .map(memeName -> parseParametersAndFetchMeme(memeName, removeFirstParameter.apply(payload), payload.getMetadata().getAuthor()))
                .orElseGet(() -> "I need to know what meme to create. List of known memes: "
                        + String.join(", ", memeTemplateLoader.loadKnownMemes()));
    }

    private String parseParametersAndFetchMeme(String memeName, List<String> parameters, String author) {
        return memeTemplateLoader.load(memeName)
                .map(template -> parseParametersAndFetchMeme(template, parameters, author))
                .orElseGet(() -> "I'm not sure how to create the meme '" + memeName + "'.");
    }

    private String parseParametersAndFetchMeme(MemeTemplate memeTemplate, List<String> parameters, String author) {
        var memeParameters = memeParameterParser.parseMemeParameters(parameters);
        return memeGenerator.populateTemplate(memeTemplate, memeParameters)
                .map(template -> fetchOrGenerateMeme(template, parameters, author))
                .orElseGet(() -> "Invalid parameters for meme '" + memeTemplate.getName()
                        + "'. Please provide: " + memeTemplate.getUsage());
    }

    private String fetchOrGenerateMeme(MemeTemplate memeTemplate, List<String> parameters, String author) {
        return savedMemeService.findExisting(memeTemplate.getName(), parameters)
                .map(SavedMeme::getUrl)
                .orElse(generateMeme(memeTemplate, parameters, author));
    }

    private String generateMeme(MemeTemplate template, List<String> parameters, String author) {
        return memeGenerator.fromTemplate(template)
                .flatMap(memeImgurWriter::write)
                .map(memeUrl -> savedMemeService.save(template.getName(), memeUrl, author, parameters))
                .map(SavedMeme::getUrl)
                .orElse("I ran into a problem while uploading the meme.");
    }
}
