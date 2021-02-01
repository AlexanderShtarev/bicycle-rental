package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.RentalDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.builder.RentalQueryBuilder;
import com.epam.jwd.dao.constant.RentalFieldsConstant;
import com.epam.jwd.domain.Inventory;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlRentalDao extends AbstractJDBCDao<Rental, Long> implements RentalDao {
    private static MySqlRentalDao instance;
    QueryBuilder builder = RentalQueryBuilder.RENTAL_QUERY_BUILDER;

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
    public String getSelectQuery() {
        return SQL_GET_ALL_RENTALS;
    }

    @Override
    public String getCreateQuery() {
        return SQL_ADD_RENTAL;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_RENTAL;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_RENTAL;
    }

    @Override
    protected String getCountQuery() {
        return null;
    }

    @Override
    protected List<Rental> parseResultSet(ResultSet rs) throws DaoException {
        List<Rental> list = new LinkedList<>();
        try {
            Rental rental = Rental.builder()
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
            list.add(rental);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Rental rental) throws DaoException {
        try {
            ps.setInt(1, rental.getProductQty());
            ps.setDate(2, new Date(rental.getRentalDate().getTime()));
            ps.setDate(3, new Date(rental.getReturnDate().getTime()));
            ps.setDouble(4, rental.getTotal());
            ps.setLong(5, rental.getInventory().getId());
            ps.setInt(6, rental.getStatus().getId());
            ps.setLong(7, rental.getUser().getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Rental rental) throws DaoException {
        try {
            ps.setInt(1, rental.getProductQty());
            ps.setDate(2, new Date(rental.getRentalDate().getTime()));
            ps.setDate(3, new Date(rental.getReturnDate().getTime()));
            ps.setDouble(4, rental.getTotal());
            ps.setLong(5, rental.getInventory().getId());
            ps.setInt(6, rental.getStatus().getId());
            ps.setLong(7, rental.getUser().getId());
            ps.setLong(8, rental.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Rental> getAll(Connection con) throws DaoException {
        return super.getAll(con);
    }

    @Override
    public List<Rental> getByCriteria(Connection con, Criteria<? extends Rental> criteria) throws DaoException {
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public Long add(Connection con, Rental rental) throws DaoException {
        Long id = super.add(con, rental);
        /*rental.setId(id);*/
        return id;
    }

    @Override
    public void update(Connection con, Rental rental) throws DaoException {
        super.update(con, rental);
    }

    @Override
    public void delete(Connection con, Long rentalId) throws DaoException {
        super.delete(con, rentalId);
    }

}
