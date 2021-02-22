package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.CartService;
import com.epam.jwd.service.impl.CartServiceImpl;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewCartAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewCartAction.class);

    public ViewCartAction() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Get cart info request received");

        CartService cartService = CartServiceImpl.getInstance();
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(RequestConstant.CART_BEAN);

        JSONObject answer = new JSONObject();
        answer.put(RequestConstant.SIZE, cartService.getSize(cart));
        answer.put(RequestConstant.TOTAL_PRICE, cartService.getTotalPrice(cart));
        response.setContentType("application/json");
        response.getWriter().write(answer.toJSONString());
    }
}
