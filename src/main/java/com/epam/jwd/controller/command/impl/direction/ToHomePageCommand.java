package com.epam.jwd.controller.command.impl.direction;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToHomePageCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToHomePageCommand.class);

    public ToHomePageCommand() {
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {

        LOGGER.debug("To home page command received");
        return new ViewResolver(PageConstant.HOME_PAGE);
    }

}
