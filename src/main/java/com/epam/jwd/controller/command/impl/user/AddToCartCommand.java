package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.impl.Cart;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddToCartCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String prodId = request.getParameter(RequestConstant.PRODUCT_ID);
        String qty = request.getParameter(RequestConstant.PRODUCT_QTY);
        User user = (User) request.getSession().getAttribute(RequestConstant.USER);

        ProductService productService = ServiceFactory.getInstance().getProductService();

        ProductCriteria productCriteria = ProductCriteria.builder().id(Long.valueOf(prodId)).build();
        Product product = productService.getSingleProductByCriteria(productCriteria);

        Cart cart = Cart.getInstance();
        cart.addToCart(product, Integer.parseInt(qty));

        request.getSession().setAttribute(RequestConstant.CART, cart);

        forward(RequestConstant.PAGE);
    }
    
}
