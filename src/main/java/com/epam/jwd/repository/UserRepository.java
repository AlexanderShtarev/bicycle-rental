package com.epam.jwd.repository;

import com.epam.jwd.dao.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.*;

public class UserRepository {
    private static UserRepository instance;

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final CreditCardDao creditCardDao;
    private final ShoppingCartDao shoppingCartDao;
    private final ShoppingCartItemDao shoppingCartItemDao;
    private final TokenDao tokenDao;

    public UserRepository() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
        this.userRoleDao = daoFactory.getUserRoleDao();
        this.creditCardDao = daoFactory.getCreditCardDao();
        this.shoppingCartDao = daoFactory.getShoppingCartDao();
        this.shoppingCartItemDao = daoFactory.getShoppingCartItemDao();
        this.tokenDao = daoFactory.getTokenDao();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }


    public Optional<User> getUserById(int id) throws DaoException {
        Optional<User> user = userDao.getById(id);
        if (user.isPresent()) {
            user.get().setRoles(userRoleDao.getUserRoles(id));
            List<CreditCard> cards = creditCardDao.getUserCards(id);
            if (!cards.isEmpty()) {
                user.get().setCards(cards);
            }
        }
        return user;
    }

    public List<CreditCard> getCardsByUserID(int userID) throws DaoException {
        return creditCardDao.getUserCards(userID);
    }

    public void insertUser(Connection connection, User user) throws DaoException {
        userDao.insert(connection, user);
        userRoleDao.insertAllByUserId(connection, user.getRoles(), user.getId());
    }

    public Optional<User> getUserByEmail(String email) throws DaoException {
        Optional<User> user = userDao.getByEmail(email);
        if (user.isPresent()) {
            user.get().setRoles(userRoleDao.getUserRoles(user.get().getId()));
            List<CreditCard> cards = creditCardDao.getUserCards(user.get().getId());
            if (!cards.isEmpty()) {
                user.get().setCards(cards);
            }
        }
        return user;
    }

    public void updateUser(Connection con, User user) throws DaoException {
        userDao.update(con, user);
    }


    public void removeUserCard(Connection con, int userId, int cardId) throws DaoException {
        creditCardDao.remove(con, cardId);
    }

    public void addUserCard(Connection con, int userID, CreditCard card) throws DaoException {
        creditCardDao.insert(con, card);
        creditCardDao.insertAllByUserId(con, Collections.singletonList(card), userID);
    }

    public void updateUserRoles(Connection con, int userId, List<UserRole> roles) throws DaoException {
        userRoleDao.removeUserRoles(con, userId);
        userRoleDao.insertAllByUserId(con, roles, userId);
    }

    public void removeUser(Connection con, int userId) throws DaoException {
        userDao.remove(con, userId);
    }

    private void setUserRoles(User user) throws DaoException {
        user.setRoles(userRoleDao.getUserRoles(user.getId()));
    }

    public List<User> getAllUsers() throws DaoException {
        List<User> users = userDao.getAll();
        for (User user : users) {
            setUserRoles(user);
        }
        return users;
    }

    public ShoppingCart getUserCart(int userId) throws DaoException {
        ShoppingCart cart = shoppingCartDao.getCartByUserId(userId);
        Map<Product, Integer> productItems = shoppingCartItemDao.getShoppingCartItems(cart.getId());
        Map<Product, Integer> items = new HashMap<>();
        if (!productItems.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : productItems.entrySet()) {
                Product product = entry.getKey();
                ProductRepository.getInstance().setProductLinks(product);
                items.put(product, entry.getValue());
            }
            cart.setProductMap(items);
        }
        return cart;
    }

    public void createCart(Connection con, Integer id) throws DaoException {
        shoppingCartDao.createCart(con, id);
    }

    public void updateCart(Connection con, ShoppingCart cart) throws DaoException {
        shoppingCartItemDao.removeItems(con, cart.getId());
        if (cart.getProductMap() != null && !cart.getProductMap().isEmpty()) {
            shoppingCartItemDao.insertItems(con, cart.getProductMap(), cart.getId());
        }
    }

    public void updateToken(Connection con, Token token) throws DaoException {
        tokenDao.update(con, token);
    }

    public void createToken(Connection con, Token token) throws DaoException {
        tokenDao.insert(con, token);
    }

    public void removeToken(Connection con, int tokenId) throws DaoException {
        tokenDao.remove(con, tokenId);
    }

    public Optional<Token> getUserToken(String token) throws DaoException {
        return tokenDao.getTokenByInputToken(token);
    }
}
