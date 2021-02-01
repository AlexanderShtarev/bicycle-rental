package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToHomePageCommand extends Command {

    public ToHomePageCommand() {
    }

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute(RequestConstant.PAGE, PageConstant.HOME_PAGE);
        forward(PageConstant.HOME_PAGE);
    }

}
