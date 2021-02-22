package com.epam.jwd.service;

import com.epam.jwd.domain.Rental;
import com.epam.jwd.exception.ServiceException;

import java.util.List;

public interface RentalService {
    List<Rental> getALl() throws ServiceException;

    Rental getById(int rentalId) throws ServiceException;

    List<Rental> getRentalsByUserId(int userId) throws ServiceException;

    void createRental(Rental rental) throws ServiceException;

    void updateRental(Rental rental) throws ServiceException;
}
