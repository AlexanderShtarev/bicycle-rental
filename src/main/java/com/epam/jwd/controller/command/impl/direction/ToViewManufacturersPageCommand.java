package com.epam.jwd.controller.command.impl.direction;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToViewManufacturersPageCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToViewCategoriesPageCommand.class);

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("To view manufacturers command received");
        return new ViewResolver(PageConstant.MANUFACTURERS_VIEW);
    }

}
