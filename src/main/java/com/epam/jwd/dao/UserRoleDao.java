package com.epam.jwd.dao;

import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> getRolesByUserId (Long id) throws DaoException;

    boolean updateUserRole(User user) throws DaoException;

}
