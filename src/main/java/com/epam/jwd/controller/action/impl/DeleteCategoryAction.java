package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategoryAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCategoryAction.class);
    private final ProductService productService;

    public DeleteCategoryAction() {
        this.productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Delete category action received");

        String id = request.getParameter(RequestConstant.CATEGORY_ID);
        productService.removeCategory(Integer.parseInt(id));
    }

}
