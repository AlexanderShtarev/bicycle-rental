package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Manufacturer;
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

public class ViewManufacturersAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewManufacturersAction.class);
    private final ProductService productService;

    public ViewManufacturersAction() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("View manufacturers action received");

        List<Manufacturer> manufacturers = productService.getAllManufacturers();

        if (!manufacturers.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseManufacturersToJSON(manufacturers));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
