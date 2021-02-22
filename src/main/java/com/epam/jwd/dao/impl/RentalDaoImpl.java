package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.RentalDao;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RentalDaoImpl extends AbstractDao<Rental> implements RentalDao {
    private static final int ID_PARAMETER_INDEX = 5;
    private static RentalDaoImpl instance = null;

    private static final String SQL_GET_RENTALS =
            "SELECT rental.id, rental.user_id,rental.rental_date, rental.return_date, rental.status_id\n" +
                    "FROM rental\n";

    private static final String SQL_GET_BY_ID = SQL_GET_RENTALS + "WHERE id = ?\n";

    private static final String SQL_GET_BY_USER_ID = SQL_GET_RENTALS + "WHERE user_id = ?";

    private static final String SQL_GET_BY_STATUS = SQL_GET_RENTALS + "WHERE status_id = ?";

    private static final String SQL_INSERT_RENTAL =
            "INSERT INTO rental(user_id, rental_date, return_date, status_id) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE_RENTAL =
            "UPDATE rental SET rental.user_id = ?, rental.rental_date = ?, rental.return_date = ?,\n" +
                    "rental.status_id = ? WHERE rental.id = ?";


    public static RentalDaoImpl getInstance() {
        if (instance == null) {
            instance = new RentalDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Rental> getAll() throws DaoException {
        return super.getAll(SQL_GET_RENTALS);
    }

    @Override
    public Optional<Rental> getById(int id) throws DaoException {
        return super.getByField(SQL_GET_BY_ID, id).stream().findFirst();
    }

    @Override
    public List<Rental> getByUserId(int id) throws DaoException {
        return super.getByField(SQL_GET_BY_USER_ID, id);
    }

    @Override
    public Optional<Rental> getByStatus(RentalStatus status) throws DaoException {
        return super.getByField(SQL_GET_BY_STATUS, status.getId()).stream().findFirst();
    }

    @Override
    public void insert(Connection connection, Rental entity) throws DaoException {
        super.insert(connection, SQL_INSERT_RENTAL, entity);
    }

    @Override
    public void update(Connection connection, Rental entity) throws DaoException {
        super.updateByField(connection, SQL_UPDATE_RENTAL, entity, ID_PARAMETER_INDEX, entity.getId());
    }

    @Override
    public void remove(Connection connection, int id) throws DaoException {
        throw new UnsupportedOperationException("Removing rentals is unsupported");
    }

    @Override
    protected Rental mapToEntity(ResultSet rs) throws SQLException {
        return Rental.builder()
                .id(rs.getInt("rental.id"))
                .user(User.builder().id(rs.getInt("rental.user_id")).build())
                .rentalDate(rs.getDate("rental.rental_date"))
                .returnDate(rs.getDate("rental.return_date"))
                .status(RentalStatus.resolveRentalStatusById(rs.getInt("rental.status_id")))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Rental entity) throws SQLException {
        ps.setInt(1, entity.getUser().getId());
        ps.setObject(2, new java.sql.Timestamp(entity.getRentalDate().getTime()));
        ps.setObject(3, new java.sql.Timestamp(entity.getReturnDate().getTime()));
        ps.setInt(4, entity.getStatus().getId());
    }
}
