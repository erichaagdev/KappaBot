package com.gorlah.kappabot.subcommand.root.meme;

import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import com.gorlah.kappabot.subcommand.root.meme.util.creator.MemeCreationException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public abstract class MemeSubcommand extends AbstractCommand {

    @Override
    public String process(CommandPayload payload) {
        val parameters = payload.getParameters();

        if (parameters.isEmpty()) {
            return "I need some text to write on the image.";
        }

        try {
            return doProcess(payload);
        } catch (MemeCreationException e) {
            log.error(e.getMessage(), e);
            return "I had a problem creating that meme for you.";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return getErrorText();
        }
    }

    protected abstract String doProcess(CommandPayload command) throws Exception;
}
