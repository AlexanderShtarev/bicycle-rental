package com.epam.jwd.dao;

import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface UserRoleDao {

    List<UserRole> getUserRoles(int userId) throws DaoException;

    void insertAllByUserId(Connection connection, List<UserRole> userRoles, int userId) throws DaoException;

    void removeUserRoles(Connection con, Integer id) throws DaoException;
}
