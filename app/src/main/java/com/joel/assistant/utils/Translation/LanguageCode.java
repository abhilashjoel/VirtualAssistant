package com.joel.assistant.utils.Translation;

import java.util.HashMap;

/**
 * Created by Joel on 5/13/2016.
 */
public class LanguageCode {
    private static LanguageCode lCode = new LanguageCode();
    public HashMap<String, String> codes;

    private LanguageCode() {
        codes = new HashMap<>();
        codes.put("french", "fr");
        codes.put("italian", "it");
        codes.put("russian", "ru");
    }

    public static String getCode(String lang) {

        String c = null;
        if (lCode.codes.containsKey(lang))
            c = lCode.codes.get(lang);

        return c;
    }
}
