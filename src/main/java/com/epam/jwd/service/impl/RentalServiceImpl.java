package com.epam.jwd.service.impl;

import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.repository.RentalRepository;
import com.epam.jwd.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RentalServiceImpl implements RentalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalServiceImpl.class);
    private static RentalServiceImpl instance;

    private final RentalRepository rentalRepository;

    private RentalServiceImpl() {
        this.rentalRepository = RentalRepository.getInstance();
    }

    public static RentalServiceImpl getInstance() {
        if (instance == null) {
            instance = new RentalServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Rental> getALl() throws ServiceException {
        try {
            return rentalRepository.getAll();
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting all rentals: ", e);
            throw new ServiceException("Dao provided exception while getting all rentals: ", e);
        }
    }

    @Override
    public Rental getById(int rentalId) throws ServiceException {
        try {
                return rentalRepository.getRentalById(rentalId).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting rental by id: ", e);
            throw new ServiceException("Dao provided exception while getting rental by id: ", e);
        }
    }

    @Override
    public List<Rental> getRentalsByUserId(int userId) throws ServiceException {
        try {
            return rentalRepository.getRentalByUserId(userId);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting rental by user id: ", e);
            throw new ServiceException("Dao provided exception while getting rental by user id: ", e);
        }
    }

    @Override
    public void createRental(Rental rental) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    rentalRepository.insertRental(con, rental));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while inserting rental: ", e);
            throw new ServiceException("Dao provided exception while inserting rental: ", e);
        }
    }

    @Override
    public void updateRental(Rental rental) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    rentalRepository.updateRental(con, rental));
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while updating rental: ", e);
            throw new ServiceException("Dao provided exception while updating rental: ", e);
        }
    }

}
