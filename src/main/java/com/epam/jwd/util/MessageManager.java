package com.epam.jwd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Util class for changing JSP page's language.
 *
 * @see ResourceBundle
 * @see Locale
 * @see MessageConstant
 */
public class MessageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);
    public static final MessageManager INSTANCE = new MessageManager();

    private final String RESOURCES = "page_content";
    private ResourceBundle resourceBundle;


    private MessageManager() {
        LOGGER.debug("Initializing Message Manager");
        resourceBundle = ResourceBundle.getBundle(RESOURCES, Locale.getDefault());
    }

    /**
     * Gets message from resource file.
     *
     * @param key the key of property.
     * @return the property.
     */
    public String getMessage(String key) {
        LOGGER.trace("Getting message from resource file");

        return resourceBundle.getString(key);
    }

    /**
     * Change language of jsp page.
     *
     * @param locale the locale.
     */
    public void changeLocale(Locale locale) {
        LOGGER.debug("Changing locale");

        resourceBundle = ResourceBundle.getBundle(RESOURCES, locale);
    }

}
