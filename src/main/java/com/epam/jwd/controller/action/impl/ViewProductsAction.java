package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;
import com.epam.jwd.util.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewProductsAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewProductsAction.class);
    private final ProductService productService;

    public ViewProductsAction() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("view products action received");

        List<Product> products = productService.getAllProducts();

        if (!products.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseProductsToJSON(products));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
