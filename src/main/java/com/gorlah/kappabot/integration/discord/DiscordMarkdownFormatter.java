package com.gorlah.kappabot.integration.discord;

import com.gorlah.kappabot.function.BotRequestEndpoint;
import com.gorlah.kappabot.message.EndpointMarkdownFormatter;
import com.gorlah.kappabot.message.AbstractMarkdownFormatter;
import org.springframework.stereotype.Component;

@Component
public class DiscordMarkdownFormatter extends AbstractMarkdownFormatter implements EndpointMarkdownFormatter {

    @Override
    public String getItalicsBegin() {
        return "*";
    }

    @Override
    public String getItalicsEnd() {
        return "*";
    }

    @Override
    public String getBoldBegin() {
        return "**";
    }

    @Override
    public String getBoldEnd() {
        return "**";
    }

    @Override
    public String getBoldItalicsBegin() {
        return "***";
    }

    @Override
    public String getBoldItalicsEnd() {
        return "***";
    }

    @Override
    public String getUnderlineBegin() {
        return "__";
    }

    @Override
    public String getUnderlineEnd() {
        return "__";
    }

    @Override
    public String getUnderlineItalicsBegin() {
        return "__*";
    }

    @Override
    public String getUnderlineItalicsEnd() {
        return "*__";
    }

    @Override
    public String getUnderlineBoldBegin() {
        return "__**";
    }

    @Override
    public String getUnderlineBoldEnd() {
        return "**__";
    }

    @Override
    public String getUnderlineBoldItalicsBegin() {
        return "__***";
    }

    @Override
    public String getUnderlineBoldItalicsEnd() {
        return "***__";
    }

    @Override
    public String getStrikethroughBegin() {
        return "~~";
    }

    @Override
    public String getStrikethroughEnd() {
        return "~~";
    }

    @Override
    public String getCodeBegin() {
        return "`";
    }

    @Override
    public String getCodeEnd() {
        return "`";
    }

    @Override
    public String getCodeBlockBegin() {
        return "```";
    }

    @Override
    public String getCodeBlockEnd() {
        return "```";
    }

    @Override
    public String getCodeBlockWithLanguageBegin(String language) {
        return "```" + language + "\n";
    }

    @Override
    public String getCodeBlockWithLanguageEnd(String language) {
        return "```";
    }

    @Override
    public String getQuoteBegin() {
        return "> ";
    }

    @Override
    public String getQuoteEnd() {
        return "";
    }

    @Override
    public String getQuoteBlockBegin() {
        return ">>> ";
    }

    @Override
    public String getQuoteBlockEnd() {
        return "";
    }

    @Override
    public String getSpoilerBegin() {
        return "||";
    }

    @Override
    public String getSpoilerEnd() {
        return "||";
    }

    @Override
    public BotRequestEndpoint getFormatterFor() {
        return BotRequestEndpoint.DISCORD;
    }
}
