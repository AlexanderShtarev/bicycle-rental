package com.epam.jwd.service.impl;

import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.repository.ProductRepository;
import com.epam.jwd.repository.UserRepository;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.MailSender;
import com.lambdaworks.crypto.SCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.*;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static UserServiceImpl instance;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private UserServiceImpl() {
        this.userRepository = UserRepository.getInstance();
        this.productRepository = ProductRepository.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        try {
            return userRepository.getUserById(id).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting user by id: ", e);
            throw new ServiceException("Dao provided exception while getting user by id: ", e);
        }
    }

    @Override
    public List<CreditCard> getUserCreditCards(int userID) throws ServiceException {
        try {
            return userRepository.getCardsByUserID(userID);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting user cards by id: ", e);
            throw new ServiceException("Dao provided exception while getting user cards by id: ", e);
        }
    }

    @Override
    public boolean register(User user) throws ServiceException {
        try {
            if (this.getUserByEmail(user.getEmail()) != null) {
                LOGGER.trace("Can't register user, email is taken");
                return false;
            }
            user.setRoles(Collections.singletonList(UserRole.USER));
            user.setBalance(BigDecimal.ZERO);
            user.setPassword(SCryptUtil.scrypt(user.getPassword(), 16, 16, 16));
            user.setStatus(UserStatus.NON_ACTIVE);
            TransactionHandler.runInTransaction(con -> {
                userRepository.insertUser(con, user);
                userRepository.createCart(con, user.getId());
            });
            return true;
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while registering user: ", e);
            throw new ServiceException("Dao provided exception while registering user: ", e);
        }
    }

    @Override
    public User login(String email, String password) throws ServiceException {
        try {
            Optional<User> user = userRepository.getUserByEmail(email);
            if (user.isPresent()) {
                if (SCryptUtil.check(password, user.get().getPassword())) {
                    return user.get();
                }
            }
            return null;
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while logging user: ", e);
            throw new ServiceException("Dao provided exception while logging user: ", e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.updateUser(con, user));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while updating user: ", e);
            throw new ServiceException("Dao provided exception while updating user: ", e);
        }
    }

    @Override
    public void removeUserCard(int userId, int cardId) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.removeUserCard(con, userId, cardId));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while removing user card: ", e);
            throw new ServiceException("Dao provided exception while removing user card: ", e);
        }
    }

    @Override
    public void addUserCard(int userId, CreditCard card) throws ServiceException {
        try {

            TransactionHandler.runInTransaction(con ->
                    userRepository.addUserCard(con, userId, card));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while inserting user card: ", e);
            throw new ServiceException("Dao provided exception while inserting user card: ", e);
        }
    }

    @Override
    public void updateUserRole(int userId, List<UserRole> roles) throws ServiceException {
        try {

            TransactionHandler.runInTransaction(con ->
                    userRepository.updateUserRoles(con, userId, roles));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while updating user roles: ", e);
            throw new ServiceException("Dao provided exception while updating user roles: ", e);
        }
    }

    @Override
    public void removeUser(int userId) throws ServiceException {
        try {

            TransactionHandler.runInTransaction(con ->
                    userRepository.removeUser(con, userId));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while removing user: ", e);
            throw new ServiceException("Dao provided exception while removing user: ", e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            return userRepository.getUserByEmail(email).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting user by email: ", e);
            throw new ServiceException("Dao provided exception while getting user by email: ", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userRepository.getAllUsers();
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting all users: ", e);
            throw new ServiceException("Dao provided exception while getting all users: ", e);
        }
    }

    @Override
    public ShoppingCart getUserCart(int id) throws ServiceException {
        try {
            return userRepository.getUserCart(id);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting user cart: ", e);
            throw new ServiceException("Dao provided exception while getting user cart: ", e);
        }
    }

    @Override
    public void updateUserCart(ShoppingCart cart, int userId) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.updateCart(con, cart));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while updating user cart: ", e);
            throw new ServiceException("Dao provided exception while updating user cart: ", e);
        }
    }

    @Override
    public void sendMailMessage(String subject, String body, String email) throws ServiceException {
        try {
            MailSender.getInstance().sendEmail(body, subject, email);
        } catch (MessagingException e) {
            LOGGER.error("Message sender provided exception while sending: ", e);
            throw new ServiceException("Message sender provided exception while sending: ", e);
        }
    }

    @Override
    public void updateToken(Token token) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.updateToken(con, token)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao exception while updating token: ", e);
            throw new ServiceException("Dao exception while updating token: ", e);
        }
    }

    @Override
    public Token createToken(User user) throws ServiceException {
        Token token = new Token(user);
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.createToken(con, token)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao exception while creating token: ", e);
            throw new ServiceException("Dao exception while creating token: ", e);
        }
        return token;
    }

    @Override
    public Token getUserToken(String token) throws ServiceException {
        try {
            return userRepository.getUserToken(token).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao exception while getting user token: ", e);
            throw new ServiceException("Dao exception while getting user token: ", e);
        }
    }

    public void removeToken(int tokenId) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    userRepository.removeToken(con, tokenId)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao exception while removing token: ", e);
            throw new ServiceException("Dao exception while removing token: ", e);
        }
    }

}
