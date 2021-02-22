package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.builder.ProductBuilder;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateProductCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductCommand.class);
    ProductService productService;

    public UpdateProductCommand() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Update product command received");

        ViewResolver resolver = new ViewResolver(PageConstant.PRODUCTS_VIEW);
        int productId = Integer.parseInt(request.getParameter(RequestConstant.PRODUCT_ID));
        Product product = productService.getProductById(productId);

        List<String> errors = ProductBuilder.buildProductForUpdate(request, product);

        if (errors.isEmpty()) {
            productService.updateProduct(product);
            resolver.setResolveAction(ResolveAction.REDIRECT);
        } else {
            request.setAttribute(RequestConstant.ERROR, errors);
        }

        return resolver;
    }
}
