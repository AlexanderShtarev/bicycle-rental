package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.RentalDao;
import com.epam.jwd.dao.constant.RentalFieldsConstant;
import com.epam.jwd.domain.Inventory;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlRentalDao extends GenericDao<Rental> implements RentalDao {
    private static MySqlRentalDao instance;

    private static final String SQL_GET_ALL_RENTALS =
            "SELECT inventory_id, product_quantity, user_id, rental_date, return_date, total, status_id\n" +
                    "FROM rental";

    private static final String SQL_ADD_RENTAL =
            "INSERT INTO rental(inventory_id, product_quantity, user_id, rental_date, return_date, total, status_id)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_RENTAL =
            "UPDATE rental\n" +
                    "SET inventory_id = ?, product_quantity = ?, user_id = ?, rental_date = ?, return_date = ?, total = ?, status_id = ?\n" +
                    "WHERE rental.id = ?;";

    private static final String SQL_DELETE_RENTAL =
            "DELETE FROM rental\n" +
                    "WHERE rental.id = ?";

    public static MySqlRentalDao getInstance() {
        if (instance == null)
            instance = new MySqlRentalDao();
        return instance;
    }

    @Override
    protected Rental mapToEntity(ResultSet rs) throws SQLException {

        return Rental.builder()
                .id(rs.getLong(RentalFieldsConstant.RENTAL_ID))
                .productQty(rs.getInt(RentalFieldsConstant.RENTAL_PRODUCT_QTY))
                .rentalDate(rs.getDate(RentalFieldsConstant.RENTAL_RENTAL_DATE))
                .returnDate(rs.getDate(RentalFieldsConstant.RENTAL_RETURN_DATE))
                .total(rs.getDouble(RentalFieldsConstant.RENTAL_TOTAL))
                .inventory(Inventory.builder()
                        .id(rs.getLong(RentalFieldsConstant.RENTAL_INVENTORY_ID))
                        .build())
                .status(RentalStatus.resolveStatusById(rs.getInt(RentalFieldsConstant.RENTAL_STATUS_ID)))
                .user(User.builder()
                        .id(rs.getLong(RentalFieldsConstant.RENTAL_USER_ID))
                        .build())
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Rental rental) throws SQLException {

        ps.setInt(1, rental.getProductQty());
        ps.setDate(2, new Date(rental.getRentalDate().getTime()));
        ps.setDate(3, new Date(rental.getReturnDate().getTime()));
        ps.setDouble(4, rental.getTotal());
        ps.setLong(5, rental.getInventory().getId());
        ps.setInt(6, rental.getStatus().getId());
        ps.setLong(7, rental.getUser().getId());

    }

}
