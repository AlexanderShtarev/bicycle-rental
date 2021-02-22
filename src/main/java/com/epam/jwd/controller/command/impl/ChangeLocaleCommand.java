package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.util.MessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeLocaleCommand.class);

    public ChangeLocaleCommand() {
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Change locale command received");

        HttpSession session = request.getSession();

        Locale locale = (Locale) session.getAttribute(RequestConstant.LOCALE);
        String language = locale == null ? "en" : locale.getLanguage();

        if (language.equals("en")) {
            session.setAttribute(RequestConstant.LOCALE, new Locale("ru", "RU"));
            MessageManager.INSTANCE.changeLocale(new Locale("ru", "RU"));
        } else if (language.equals("ru")) {
            MessageManager.INSTANCE.changeLocale(new Locale("en", "US"));
            session.setAttribute(RequestConstant.LOCALE, new Locale("en", "US"));
        }

        return new ViewResolver(PageConstant.INDEX_PAGE);
    }

}
