package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.domain.*;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductCommand extends Command {
    ProductService productService;

    public SearchProductCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        productService = serviceFactory.getProductService();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productId = request.getParameter("prodId");
        String productType = request.getParameter(RequestConstant.PRODUCT_TYPE);
        String productProducer = request.getParameter(RequestConstant.PRODUCT_PRODUCER);
        String productModel = request.getParameter(RequestConstant.PRODUCT_MODEL);
        Double productPrice = Double.parseDouble(request.getParameter(RequestConstant.PRODUCT_PRICE));

        User user = (User) session.getAttribute(RequestConstant.USER);


        if (productId != null) {
            Long id = Long.valueOf(productId);

            ProductCriteria criteria = ProductCriteria.builder()
                    .id(id)
                    .type(ProductType.builder().name(productType).build())
                    .producer(ProductProducer.builder().name(productProducer).build())
                    .model(productModel)
                    .pricePerHour(productPrice)
                    .build();

            List<Product> products = productService.getAllProductsByCriteria(criteria);

            if (products != null) {
                request.setAttribute(RequestConstant.PAGE, PageConstant.SEARCH_PRODUCT_PAGE);
                request.setAttribute(RequestConstant.PRODUCTS, products);
                if (user.getRoles().contains(UserRole.CLIENT)) {
                    forward(PageConstant.SEARCH_PRODUCT_PAGE_USER);
                }
            } else {
              request.setAttribute(RequestConstant.ERROR, "Can't find any products");
              forward(PageConstant.ERROR_PAGE);
            }

        }

    }

}
