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
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddProductCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddProductCommand.class);
    private final ProductService productService;

    public AddProductCommand() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Add product command received");

        ViewResolver resolver = new ViewResolver(PageConstant.PRODUCTS_VIEW);

        try {
            Product product = ProductBuilder.getProductFromRequest(request);
            List<String> errors = ProductValidator.checkProduct(product);
            if (errors.isEmpty()) {
                productService.insertProduct(product);
                resolver.setResolveAction(ResolveAction.REDIRECT);
            } else {
                request.setAttribute(RequestConstant.ERROR, errors);
            }
        } catch (NumberFormatException e) {
            LOGGER.warn("Can't create product, ", e);
            request.setAttribute(RequestConstant.ERROR,
                    MessageManager.INSTANCE.getMessage(MessageConstant.SOMETHING_IS_WRONG));
        }

        return resolver;
    }
}
