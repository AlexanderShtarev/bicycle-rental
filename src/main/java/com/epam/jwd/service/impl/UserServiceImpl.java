package com.epam.jwd.service.impl;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.dao.UserRoleDao;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private final TransactionHandler transactionHandler;

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    private UserServiceImpl() {
        this.transactionHandler = new TransactionHandler();

        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
        this.userDao = daoFactory.getUserDao();
        this.userRoleDao = daoFactory.getUserRoleDao();

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) ApplicationContext.APPLICATION_CONTEXT.getCache(User.class);
    }

    @Override
    public List<User> getAllUsersByCriteria(Criteria<? extends User> userCriteria) {
        UserCriteria criteria = (UserCriteria) userCriteria;
        return this.filterByCriteria(criteria);
    }

    private List<User> filterByCriteria(Criteria<? extends User> userCriteria) {
        Collection<User> userCache = ApplicationContext.APPLICATION_CONTEXT.getCache(User.class);
        UserCriteria criteria = (UserCriteria) userCriteria;
        return userCache.stream().filter(user ->
                criteria.getId() == null || user.getId().equals(criteria.getId())
                        && criteria.getName() == null || user.getName().equals(criteria.getName())
                        && criteria.getStatus() == null || user.getStatus().equals(criteria.getStatus())
                        && criteria.getEmail() == null || user.getEmail().equals(criteria.getEmail())
                        && criteria.getBalance() == null || user.getBalance().equals(criteria.getBalance())
                        && criteria.getPassword() == null || user.getPassword().equals(criteria.getPassword())
                        && criteria.getRoles() == null || user.getRoles().equals(criteria.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public User getSingleUserByCriteria(Criteria<? extends User> userCriteria) {
        List<User> users = this.filterByCriteria(userCriteria);
        return users.isEmpty() ? getSingleUserFromDb((UserCriteria) userCriteria) : users.get(0);
    }

    private User getSingleUserFromDb(UserCriteria criteria) {
        return transactionHandler.transactional(con -> {
            Optional<User> user = userDao.getSingleUserByCriteria(criteria);
            user.ifPresent(value -> value.setRoles(userRoleDao.getRolesByUserId(value.getId())));
            return user;
        }).orElseThrow(DaoException::new);
    }

    @Override
    public void registerUser(User user) {
        transactionHandler.transactional(con ->
                userDao.persist(con, user));
    }

    @Override
    public void updateUser(User user) {
        transactionHandler.transactional(con -> {
            userDao.update(con, user);
            userRoleDao.updateUserRole(user);
            return true;
        });
    }

    @Override
    public void deleteUser(Long userId) {
        transactionHandler.transactional(con ->
                userDao.deleteById(con, userId));
    }


}
