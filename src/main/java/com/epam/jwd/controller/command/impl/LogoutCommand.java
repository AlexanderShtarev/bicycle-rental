package com.epam.jwd.controller.command.impl;


import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    public LogoutCommand() {
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Logout command received");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(RequestConstant.USER);
            session.removeAttribute(RequestConstant.CART_BEAN);
            session.invalidate();
            if (request.getSession() == null) {
                LOGGER.debug("Session invalidated");
            } else {
                LOGGER.warn("Can't invalidate session");
            }
        } else {
            LOGGER.warn("Trying to logout while session invalidated");
        }

        return new ViewResolver(PageConstant.HOME_PAGE, ResolveAction.REDIRECT);
    }

}