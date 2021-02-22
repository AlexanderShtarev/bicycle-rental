package com.epam.jwd;

import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.impl.ShoppingCartDaoImpl;
import com.epam.jwd.dao.impl.ShoppingCartItemDaoImpl;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.repository.UserRepository;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.CartServiceImpl;
import com.epam.jwd.service.impl.ProductServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws ServiceException, JsonProcessingException {

        ShoppingCart cart = UserServiceImpl.getInstance().getUserCart(13);
        System.out.println(cart.getProductMap());
        Map<Product, Integer> map = cart.getProductMap();
        Product p = ProductServiceImpl.getInstance().getProductById(3);

        map.forEach((pr, i) -> System.out.println(pr.equals(p)));
        System.out.println(map.get(p));
        AtomicInteger count = new AtomicInteger(map.getOrDefault(p, 0));

        cart.getProductMap().put(p, count.incrementAndGet());

        System.out.println(cart.getProductMap());

    }

}
