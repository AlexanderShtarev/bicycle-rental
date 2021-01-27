package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction<T> {

    T execute(Connection connection) throws SQLException, DaoException;

}