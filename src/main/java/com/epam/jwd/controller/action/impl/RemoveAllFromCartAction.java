package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.CartService;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.CartServiceImpl;
import com.epam.jwd.service.impl.ProductServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class RemoveAllFromCartAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveAllFromCartAction.class);
    private ProductService productService;
    private CartService cartService;
    private UserService userService;

    public RemoveAllFromCartAction() {
        this.productService = ProductServiceImpl.getInstance();
        this.cartService = CartServiceImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Remove all action received");

        int productId = Integer.parseInt(request.getParameter(RequestConstant.PRODUCT_ID));
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(RequestConstant.CART_BEAN);
        Product product = productService.getProductById(productId);

        cartService.removeProducts(cart, product, cartService.getCount(cart, product));

        request.getSession().setAttribute(RequestConstant.CART_BEAN, cart);

        JSONObject answer = new JSONObject();
        if (product != null) {
            BigDecimal productCost = cartService.calculateCost(cartService.getCount(cart, product), product.getPrice());
            answer.put(RequestConstant.PRODUCT_COST, productCost);
        }

        answer.put(RequestConstant.SIZE, cartService.getSize(cart));
        answer.put(RequestConstant.PRODUCT_COUNT, cartService.getCount(cart, product));
        answer.put(RequestConstant.TOTAL_PRICE, cartService.getTotalPrice(cart));

        response.setContentType("application/json");
        response.getWriter().write(answer.toJSONString());
    }

}
