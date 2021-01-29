package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateProductCommand extends Command {
    ProductService productService;

    public UpdateProductCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String productId = request.getParameter(RequestConstant.PRODUCT_ID);
    }

}
