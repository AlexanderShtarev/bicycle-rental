package com.epam.jwd.service.impl;

import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ShoppingCart;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.repository.UserRepository;
import com.epam.jwd.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private static CartServiceImpl instance;
    private final UserRepository userRepository;

    public CartServiceImpl() {
        this.userRepository = UserRepository.getInstance();
    }

    public static CartServiceImpl getInstance() {
        if (instance == null) {
            instance = new CartServiceImpl();
        }
        return instance;
    }

    @Override
    public void addProduct(ShoppingCart cart, Product product) throws ServiceException {
        cart.getProductMap().merge(product, 1, Integer::sum);
        this.updateCart(cart);
    }

    @Override
    public void removeProducts(ShoppingCart cart, Product product, int number) throws ServiceException {
        Map<Product, Integer> productMap = cart.getProductMap();
        Integer count = productMap.get(product);
        if (count != null) {
            if (number > count || number < 1) {
                throw new IllegalArgumentException("Not valid number of products");
            }
            if (count - number <= 0) {
                productMap.remove(product);
            } else {
                productMap.put(product, count - number);
            }
        }
        this.updateCart(cart);
    }

    @Override
    public int getSize(ShoppingCart cart) {
        Map<Product, Integer> productMap = cart.getProductMap();
        int size = 0;
        for (Integer count : productMap.values()) {
            size += count;
        }
        return size;
    }

    @Override
    public int getCount(ShoppingCart cart, Product product) {
        Map<Product, Integer> productMap = cart.getProductMap();
        Integer count = productMap.get(product);
        return (count != null) ? count : 0;
    }

    @Override
    public BigDecimal getTotalPrice(ShoppingCart cart) {
        Map<Product, Integer> productMap = cart.getProductMap();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> productEntry : productMap.entrySet()) {
            BigDecimal productPrice = productEntry.getKey().getPrice();
            BigDecimal productCount = new BigDecimal(productEntry.getValue());
            totalPrice = totalPrice.add(productPrice.multiply(productCount));
        }
        return totalPrice;
    }

    @Override
    public void clearCart(ShoppingCart cart) throws ServiceException {
        Map<Product, Integer> productMap = cart.getProductMap();
        productMap.clear();
        this.updateCart(cart);
    }

    private void updateCart(ShoppingCart cart) throws ServiceException {
        LOGGER.debug("Updating shopping card in database");
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.updateCart(con, cart));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while updating cart: ", e);
            throw new ServiceException("Dao provided exception while updating cart: ", e);
        }
    }

    @Override
    public BigDecimal calculateCost(int itemQuantity, BigDecimal itemCost) {
        BigDecimal totalCost = BigDecimal.ZERO;
        itemCost = itemCost.multiply(new BigDecimal(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }

}
