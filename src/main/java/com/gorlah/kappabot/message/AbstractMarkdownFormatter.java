package com.gorlah.kappabot.message;

import java.util.Map;

import static com.gorlah.kappabot.message.MarkdownVariables.*;

public abstract class AbstractMarkdownFormatter implements MarkdownFormatter {

    private final Map<String, String> formatterMap;

    public AbstractMarkdownFormatter() {
        this.formatterMap = Map.ofEntries(
                Map.entry(ITALICS_BEGIN_VAR, getItalicsBegin()),
                Map.entry(ITALICS_END_VAR, getItalicsEnd()),
                Map.entry(BOLD_BEGIN_VAR, getBoldBegin()),
                Map.entry(BOLD_END_VAR, getBoldEnd()),
                Map.entry(BOLD_ITALICS_BEGIN_VAR, getBoldItalicsBegin()),
                Map.entry(BOLD_ITALICS_END_VAR, getBoldItalicsEnd()),
                Map.entry(UNDERLINE_BEGIN_VAR, getUnderlineBegin()),
                Map.entry(UNDERLINE_END_VAR, getUnderlineEnd()),
                Map.entry(UNDERLINE_ITALICS_BEGIN_VAR, getUnderlineItalicsBegin()),
                Map.entry(UNDERLINE_ITALICS_END_VAR, getUnderlineItalicsEnd()),
                Map.entry(UNDERLINE_BOLD_BEGIN_VAR, getUnderlineBoldBegin()),
                Map.entry(UNDERLINE_BOLD_END_VAR, getUnderlineBoldEnd()),
                Map.entry(UNDERLINE_BOLD_ITALICS_BEGIN_VAR, getUnderlineBoldItalicsBegin()),
                Map.entry(UNDERLINE_BOLD_ITALICS_END_VAR, getUnderlineBoldItalicsEnd()),
                Map.entry(STRIKETHROUGH_BEGIN_VAR, getStrikethroughBegin()),
                Map.entry(STRIKETHROUGH_END_VAR, getStrikethroughEnd()),
                Map.entry(CODE_BEGIN_VAR, getCodeBegin()),
                Map.entry(CODE_END_VAR, getCodeEnd()),
                Map.entry(CODE_BLOCK_BEGIN_VAR, getCodeBlockBegin()),
                Map.entry(CODE_BLOCK_END_VAR, getCodeBlockEnd()),
                Map.entry(CODE_BLOCK_LANGUAGE_BEGIN_VAR, getCodeBlockWithLanguageBegin("Java")),
                Map.entry(CODE_BLOCK_LANGUAGE_END_VAR, getCodeBlockWithLanguageEnd("Java")),
                Map.entry(QUOTE_BEGIN_VAR, getQuoteBegin()),
                Map.entry(QUOTE_END_VAR, getQuoteEnd()),
                Map.entry(QUOTE_BLOCK_BEGIN_VAR, getQuoteBlockBegin()),
                Map.entry(QUOTE_BLOCK_END_VAR, getQuoteBlockEnd()),
                Map.entry(SPOILER_BEGIN_VAR, getSpoilerBegin()),
                Map.entry(SPOILER_END_VAR, getSpoilerEnd())
        );
    }

    @Override
    public Map<String, String> getFormatterMap() {
        return formatterMap;
    }
}
