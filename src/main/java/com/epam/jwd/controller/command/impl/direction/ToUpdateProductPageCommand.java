package com.epam.jwd.controller.command.impl.direction;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateProductPageCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToUpdateProductPageCommand.class);

    ProductService productService;

    public ToUpdateProductPageCommand() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("To update product command received");
        request.setAttribute(RequestConstant.PRODUCT,
                productService.getProductById(Integer.parseInt(request.getParameter(RequestConstant.PRODUCT_ID))));

        request.setAttribute(RequestConstant.MANUFACTURERS, productService.getAllManufacturers());
        request.setAttribute(RequestConstant.CATEGORIES, productService.getAllCategories());

        return new ViewResolver(PageConstant.UPDATE_PRODUCT);
    }

}
