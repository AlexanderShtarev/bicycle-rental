package com.epam.jwd.dao;

import com.epam.jwd.exception.DaoException;

import java.sql.Connection;

public interface Transaction {

    public void execute(Connection connection) throws DaoException;

}
