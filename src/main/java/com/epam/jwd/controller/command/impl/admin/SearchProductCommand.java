package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.Product;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class SearchProductCommand extends Command {
    ProductService productService;

    public SearchProductCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String productId = request.getParameter("prodId");

        if (productId != null) {
            Long id = Long.parseLong(productId);
            Product product = productService.getProductById(id);
            System.out.println(product);
            if (product != null) {
                request.setAttribute(RequestConstant.PAGE, PageConstant.SEARCH_PRODUCT_PAGE);
                request.setAttribute(RequestConstant.PRODUCT, product);
                forward(PageConstant.SEARCH_PRODUCT_PAGE);
            } else {
              request.setAttribute(RequestConstant.ERROR, "Can't find any products");
              forward(PageConstant.ERROR_PAGE);
            }
        }

    }

}
