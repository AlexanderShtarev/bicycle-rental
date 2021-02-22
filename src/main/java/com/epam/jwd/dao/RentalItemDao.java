package com.epam.jwd.dao;

import com.epam.jwd.domain.RentalItem;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface RentalItemDao {

    List<RentalItem> getRentalItems(int rentalId) throws DaoException;

    void insertAllByRentalId(Connection connection, List<RentalItem> items, int rentalId) throws DaoException;
}
