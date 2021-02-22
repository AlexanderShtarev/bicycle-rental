package com.epam.jwd.dao;

import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> getByEmail(String email) throws DaoException;
}
