package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.impl.Cart;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.domain.Product;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class RemoveFromCartCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String prodId = request.getParameter(RequestConstant.PRODUCT_ID);

        ProductService productService = ServiceFactory.getInstance().getProductService();

        ProductCriteria criteria = ProductCriteria.builder().id(Long.valueOf(prodId)).build();

        Product product = productService.getSingleProductByCriteria(criteria);
        Cart cart = Cart.getInstance();
        cart.removeFromCart(product);
        request.getSession().setAttribute(RequestConstant.CART, cart);

        forward(RequestConstant.PAGE);

    }

}
