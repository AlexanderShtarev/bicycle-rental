/*
package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToUpdateProductPageCommand extends Command {
    ProductService productService;

    public ToUpdateProductPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String prodId = request.getParameter("prodId");

        if (prodId != null ) {
            Long id = Long.valueOf(prodId);
            request.setAttribute(RequestConstant.PRODUCT, productService.getProductById(id));
            forward(PageConstant.EDIT_PRODUCT_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Product not found");
            forward(PageConstant.ERROR_PAGE);
        }

    }

}
*/
