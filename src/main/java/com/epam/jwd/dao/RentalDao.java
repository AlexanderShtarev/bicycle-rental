package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface RentalDao {

    List<Rental> getAll(Connection con) throws DaoException;

    List<Rental> getByCriteria(Connection con, Criteria<? extends Rental> criteria) throws DaoException;

    Long add(Connection con, Rental rental) throws DaoException;

    void update(Connection con, Rental rental) throws DaoException;

    void delete(Connection con, Long rentalId) throws DaoException;

}