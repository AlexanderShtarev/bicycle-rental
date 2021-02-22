package com.epam.jwd.controller.command.impl.direction;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.builder.ProductBuilder;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToProductsPageCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToProductsPageCommand.class);
    private static final int DEFAULT_PRODUCT_LIMIT = 9;
    private static final int DEFAULT_PAGE = 1;

    private final ProductService productService;

    public ToProductsPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.productService = serviceFactory.getProductService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("To products page command received");

        ProductSearchBean bean = ProductBuilder.getProductFilterFromRequest(request, DEFAULT_PRODUCT_LIMIT, DEFAULT_PAGE);

        List<Product> products = productService.getFilteredProducts(bean);

        request.setAttribute(RequestConstant.PRODUCTS, products);
        request.setAttribute(RequestConstant.PRODUCTS_COUNT, products.size());
        request.setAttribute(RequestConstant.CATEGORIES, productService.getAllCategories());
        request.setAttribute(RequestConstant.MANUFACTURERS, productService.getAllManufacturers());
        request.setAttribute(RequestConstant.FORM_BEAN, bean);

        return new ViewResolver(PageConstant.PRODUCTS_PAGE);
    }

}