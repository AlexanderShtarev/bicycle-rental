/*
package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddProductCommand extends Command {
    ProductService productService;

    public AddProductCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String productType = request.getParameter("prodType");
        String productProducer = request.getParameter("prodProducer");
        String productModel = request.getParameter("prodModel");
        Double productPrice = Double.parseDouble(request.getParameter("prodPrice"));
        Product product = Product.builder()
                .type(ProductType.builder().name(productType).build())
                .producer(ProductProducer.builder().name(productProducer).build())
                .model(productModel)
                .pricePerHour(productPrice)
                .build();

        if (productService.addProduct(product)) {
            request.setAttribute(RequestConstant.PAGE, PageConstant.ADD_PRODUCT_PAGE);
            forward(PageConstant.ADD_PRODUCT_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Can't add product");
            request.setAttribute(RequestConstant.PAGE, PageConstant.ERROR_PAGE);
            forward(PageConstant.ERROR_PAGE);
        }


    }

}
*/
