package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AddManufacturerCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddManufacturerCommand.class);
    ProductService productService;

    public AddManufacturerCommand() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Add manufacturer command received");

        ViewResolver resolver = new ViewResolver(PageConstant.MANUFACTURERS_VIEW);
        String name = request.getParameter(RequestConstant.MANUFACTURER_NAME);
        Optional<String> error = Validator.checkString(name);

        if (error.isEmpty()) {
            if (productService.getManufacturerByName(name) == null) {
                Manufacturer manufacturer = Manufacturer.builder()
                        .name(name)
                        .build();
                productService.insertManufacturer(manufacturer);

                resolver.setResolveAction(ResolveAction.REDIRECT);
            } else {
                request.setAttribute(RequestConstant.ERROR,
                        MessageManager.INSTANCE.getMessage(MessageConstant.MANUFACTURER_ALREADY_EXIST));
            }
        } else {
            request.setAttribute(RequestConstant.ERROR, error.get());
        }

        return resolver;
    }
}
