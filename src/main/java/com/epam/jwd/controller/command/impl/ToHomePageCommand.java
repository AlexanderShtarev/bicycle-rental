package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToHomePageCommand extends Command {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute(RequestConstant.PAGE, PageConstant.HOME_PAGE);
        request.setAttribute(RequestConstant.FEATURED_PRODUCTS, productService.getFeaturedProducts());
        forward(PageConstant.HOME_PAGE);
    }

}
