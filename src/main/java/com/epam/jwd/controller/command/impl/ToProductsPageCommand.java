package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToProductsPageCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute(RequestConstant.PAGE, PageConstant.PRODUCTS_PAGE);
        forward(PageConstant.PRODUCTS_PAGE);
    }

}
