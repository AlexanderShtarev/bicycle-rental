package com.epam.jwd.repository;

import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.RentalDao;
import com.epam.jwd.dao.RentalItemDao;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RentalRepository {
    private static RentalRepository instance;

    private final RentalDao rentalDao;
    private final RentalItemDao rentalItemDao;

    public RentalRepository() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.rentalDao = daoFactory.getRentalDao();
        this.rentalItemDao = daoFactory.getRentalItemDao();
    }

    public static RentalRepository getInstance() {
        if (instance == null) {
            instance = new RentalRepository();
        }
        return instance;
    }

    public Optional<Rental> getRentalById(int rentalId) throws DaoException {
        Optional<Rental> rental = rentalDao.getById(rentalId);
        if (rental.isPresent()) {
            rental.get().setRentalItems(rentalItemDao.getRentalItems(rentalId));
        }
        return rental;
    }

    public List<Rental> getRentalByUserId(int userId) throws DaoException {
        List<Rental> rentals = rentalDao.getByUserId(userId);
        for (Rental rental : rentals) {
            rental.setRentalItems(rentalItemDao.getRentalItems(rental.getId()));
        }
        return rentals;
    }

    public void insertRental(Connection con, Rental rental) throws DaoException {
        rentalDao.insert(con, rental);
        rentalItemDao.insertAllByRentalId(con, rental.getRentalItems(), rental.getId());
    }

    public void updateRental(Connection con, Rental rental) throws DaoException {
        rentalDao.update(con, rental);
    }

    public List<Rental> getAll() throws DaoException {
        List<Rental> rentals = rentalDao.getAll();
        for (Rental rental : rentals) {
            rental.setRentalItems(rentalItemDao.getRentalItems(rental.getId()));
        }
        return rentals;
    }

}
