/*
package com.epam.jwd.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class JspMessageManagerUtil {

    private static final String BASE_NAME = "page";
    private static final Locale LOCALE = new Locale("en", "US");

    private JspMessageManagerUtil() {
    }

    public static String getMessage(String key, String locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, formatLocale(locale));
        return resourceBundle.getString(key);
    }

    private static Locale formatLocale(String locale) {
        if (locale != null) {
            String[] languageCountry = locale.split("_");
            return new Locale(languageCountry[0], languageCountry[1]);
        } else {
            Locale.setDefault(LOCALE);
            return LOCALE;
        }
    }

}
*/
