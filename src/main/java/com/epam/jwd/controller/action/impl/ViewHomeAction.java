package com.epam.jwd.controller.action.impl;

import com.epam.jwd.bean.ProductSearchBean;
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

public class ViewHomeAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewHomeAction.class);
    private static final int DEFAULT_HOME_LIMIT = 9;
    private static final String DEFAULT_HOME_SORTING = "product.id";
    private final ProductService productService;

    public ViewHomeAction() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("view home action received");

        ProductSearchBean bean = new ProductSearchBean();
        bean.setLimit(DEFAULT_HOME_LIMIT);
        bean.setDescending(true);
        bean.setSortedBy(DEFAULT_HOME_SORTING);

        List<Product> products = productService.getFilteredProducts(bean);

        if (!products.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseProductsToJSON(products));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }
    }

}
