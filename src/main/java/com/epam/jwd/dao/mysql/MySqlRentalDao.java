package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.Inventory;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlRentalDao extends GenericDao<Rental> {

    private static final String SQL_GET_ALL_RENTALS =
            "";

    private static final String SQL_ADD_RENTAL =
            "";

    private static final String SQL_UPDATE_RENTAL =
            "";

    private static final String SQL_DELETE_RENTAL =
            "";

    @Override
    protected Rental mapToEntity(ResultSet rs) throws SQLException {

        return Rental.builder()
                .id(rs.getLong("rental.id"))
                .productQty(rs.getInt("rental.product_qty"))
                .rentalDate(rs.getDate("rental.rentalDate"))
                .returnDate(rs.getDate("rental.returnDate"))
                .total(rs.getDouble("rental.total"))
                .inventory(Inventory.builder()
                        .id(rs.getLong("rental.inventory_id"))
                        .build())
                .status(RentalStatus.resolveStatusById(rs.getInt("rental.status_id")))
                .user(User.builder()
                        .id(rs.getLong("rental.user_id"))
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
