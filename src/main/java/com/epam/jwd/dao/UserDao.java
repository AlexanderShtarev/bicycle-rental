package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    List<User> getAll(Connection con) throws DaoException;

    List<User> getByCriteria(Connection con, Criteria<? extends User> criteria) throws DaoException;

    User add(Connection con, User user) throws DaoException;

    void update(Connection con, User user) throws DaoException;

    void delete(Connection con, Long userId) throws DaoException;

}