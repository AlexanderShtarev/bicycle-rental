package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.context.Context;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteProductCommand extends Command {
    Context context = ApplicationContext.APPLICATION_CONTEXT;
    ProductService productService;

    public DeleteProductCommand() {
        ServiceFactory serviceFactory = context.getServiceFactory();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("prodId"));

        if (productService.deleteProduct(productId)) {
            request.setAttribute(RequestConstant.PAGE, PageConstant.VIEW_PRODUCTS_PAGE);
            forward(PageConstant.VIEW_PRODUCTS_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Can't delete product");
            forward(RequestConstant.ERROR);
        }

    }

}
