package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.util.LocaleManagerUtil;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangeLocaleCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String language = request.getParameter(RequestConstant.LOCALE);
        String currentPage = request.getParameter(RequestConstant.PAGE);
        LocaleManagerUtil localeManager = LocaleManagerUtil.getInstance();

        if ("ru".equals(language)) {
            localeManager.setRussian();
        } else {
            localeManager.setEnglish();
        }
        request.getSession().setAttribute(RequestConstant.LOCALE, localeManager);

        forward(currentPage);
    }

}
