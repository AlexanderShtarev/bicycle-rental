package com.epam.jwd.service;

import com.epam.jwd.domain.*;
import com.epam.jwd.exception.ServiceException;

import java.util.List;

public interface UserService {

    User getUserById(int id) throws ServiceException;

    List<CreditCard> getUserCreditCards(int userID) throws ServiceException;

    boolean register(User user) throws ServiceException;

    User login(String email, String password) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void removeUserCard(int userId, int cardId) throws ServiceException;

    void addUserCard(int userId, CreditCard card) throws ServiceException;

    void updateUserRole(int userId, List<UserRole> roles) throws ServiceException;

    void removeUser(int userId) throws ServiceException;

    User getUserByEmail(String email) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    ShoppingCart getUserCart(int id) throws ServiceException;

    void updateUserCart(ShoppingCart cart, int userId) throws ServiceException;

    void sendMailMessage(String subject, String body, String email) throws ServiceException;

    void updateToken(Token token) throws ServiceException;

    Token createToken(User user) throws ServiceException;

    Token getUserToken(String token) throws ServiceException;
}
