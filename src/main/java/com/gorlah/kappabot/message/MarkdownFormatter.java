package com.gorlah.kappabot.message;

import java.util.Map;

public interface MarkdownFormatter {

    String getItalicsBegin();
    String getItalicsEnd();

    String getBoldBegin();
    String getBoldEnd();

    String getBoldItalicsBegin();
    String getBoldItalicsEnd();

    String getUnderlineBegin();
    String getUnderlineEnd();

    String getUnderlineItalicsBegin();
    String getUnderlineItalicsEnd();

    String getUnderlineBoldBegin();
    String getUnderlineBoldEnd();

    String getUnderlineBoldItalicsBegin();
    String getUnderlineBoldItalicsEnd();

    String getStrikethroughBegin();
    String getStrikethroughEnd();

    String getCodeBegin();
    String getCodeEnd();

    String getCodeBlockBegin();
    String getCodeBlockEnd();

    String getCodeBlockWithLanguageBegin(String language);
    String getCodeBlockWithLanguageEnd(String language);

    String getQuoteBegin();
    String getQuoteEnd();

    String getQuoteBlockBegin();
    String getQuoteBlockEnd();

    String getSpoilerBegin();
    String getSpoilerEnd();

    Map<String, String> getFormatterMap();
}
