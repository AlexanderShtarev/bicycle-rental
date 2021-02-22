package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Category;
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

public class ViewCategoriesAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewCategoriesAction.class);
    private final ProductService productService;

    public ViewCategoriesAction() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("view categories action received");

        List<Category> categories = productService.getAllCategories();

        if (!categories.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseCategoriesToJSON(categories));

            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
