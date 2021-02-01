package com.epam.jwd.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManagerUtil {
    private static final String PAGE_CONTENT = "page_content";

    private static LocaleManagerUtil instance;
    private Locale locale;
    private ResourceBundle resourceBundle;

    private LocaleManagerUtil() {
        locale = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle(PAGE_CONTENT, locale);
    }

    public static LocaleManagerUtil getInstance() {
        if (instance == null) {
            instance = new LocaleManagerUtil();
        }
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public void setRussian(){
        locale = new Locale("ru","RU");
        resourceBundle = ResourceBundle.getBundle(PAGE_CONTENT, locale);
    }

    public void setEnglish(){
        locale = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle(PAGE_CONTENT, locale);
    }

}
