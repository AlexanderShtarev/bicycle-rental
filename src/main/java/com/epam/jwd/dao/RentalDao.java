package com.epam.jwd.dao;

import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface RentalDao extends BaseDao<Rental> {

    List<Rental> getByUserId(int id) throws DaoException;

    Optional<Rental> getByStatus(RentalStatus status) throws DaoException;

}
