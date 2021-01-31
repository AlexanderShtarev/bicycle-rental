package com.epam.jwd.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManagerUtil {
    private static LocaleManagerUtil instance = new LocaleManagerUtil();
    private Locale current;
    private ResourceBundle resourceBundle;

    private LocaleManagerUtil() {
        current = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }

    public static LocaleManagerUtil getInstance() {
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public void setRussian(){
        current = new Locale("ru","RU");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }

    public void setEnglish(){
        current = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }

}
