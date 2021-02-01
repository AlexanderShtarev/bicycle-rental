package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.context.Context;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.validator.EntityValidator;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddProductCommand extends Command {
    Context context = ApplicationContext.APPLICATION_CONTEXT;
    ProductService productService;
    EntityValidator validator;

    public AddProductCommand() {
        ServiceFactory serviceFactory = context.getServiceFactory();
        productService = serviceFactory.getProductService();
        validator = context.getEntityValidator();
    }

    @Override
    public void process() throws ServletException, IOException {
        String productType = request.getParameter(RequestConstant.PRODUCT_TYPE);
        String productProducer = request.getParameter(RequestConstant.PRODUCT_PRODUCER);
        String productModel = request.getParameter(RequestConstant.PRODUCT_MODEL);
        Double productPrice = Double.parseDouble(request.getParameter(RequestConstant.PRODUCT_PRICE));

        Product product = Product.builder()
                .type(ProductType.builder().name(productType).build())
                .producer(ProductProducer.builder().name(productProducer).build())
                .model(productModel)
                .pricePerHour(productPrice)
                .build();

        try {
            validator.validate(product);
        } catch (ValidationException e) {
            request.setAttribute(RequestConstant.ERROR, e.getMessage());
            forward(PageConstant.ERROR_PAGE);
        }

        productService.addProduct(product);
        request.setAttribute(RequestConstant.PAGE, PageConstant.ADD_PRODUCT_PAGE);
        forward(PageConstant.ADD_PRODUCT_PAGE);


    }

}
