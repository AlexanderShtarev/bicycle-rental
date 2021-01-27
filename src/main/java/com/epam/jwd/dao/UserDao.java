package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    List<User> findAll(Connection con) throws DaoException;

    User findByCriteria(Connection con, Criteria<? extends User> criteria) throws DaoException;

    void addUser(Connection con, User user) throws DaoException;

    void updateUser(Connection con, User user) throws DaoException;

    void deleteUser(Connection con, Long userId) throws DaoException;

}