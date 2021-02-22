package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ManufacturerDao extends AbstractDao<Manufacturer> {
    private static ManufacturerDao instance;

    private static final String SQL_SELECT_MANUFACTURER = "SELECT manufacturer.id, manufacturer.name FROM manufacturer\n";

    private static final String SQL_SELECT_BY_ID = SQL_SELECT_MANUFACTURER + " WHERE id = ?\n";

    private static final String SQL_SELECT_BY_NAME = SQL_SELECT_MANUFACTURER + " WHERE name = ?";

    private static final String SQL_INSERT_MANUFACTURER = "INSERT INTO manufacturer(name) VALUES (?)\n";

    private static final String SQL_REMOVE_MANUFACTURER = "DELETE manufacturer FROM manufacturer WHERE id = ?\n";

    public static ManufacturerDao getInstance() {
        if (instance == null) {
            instance = new ManufacturerDao();
        }
        return instance;
    }

    @Override
    public List<Manufacturer> getAll() throws DaoException {
        return super.getAll(SQL_SELECT_MANUFACTURER);
    }

    @Override
    public Optional<Manufacturer> getById(int id) throws DaoException {
        return super.getByField(SQL_SELECT_BY_ID, id).stream()
                .filter(Objects::nonNull).findFirst();
    }

    @Override
    public void insert(Connection con, Manufacturer item) throws DaoException {
        super.insert(con, SQL_INSERT_MANUFACTURER, item);
    }

    @Override
    public void update(Connection connection, Manufacturer entity) throws DaoException {
        throw new UnsupportedOperationException("Updating manufacturers is not supported");
    }

    @Override
    public void remove(Connection con, int id) throws DaoException {
        super.deleteByField(con, SQL_REMOVE_MANUFACTURER, id);
    }

    @Override
    protected Manufacturer mapToEntity(ResultSet rs) throws SQLException {
        return Manufacturer.builder()
                .id(rs.getInt("manufacturer.id"))
                .name(rs.getString("manufacturer.name"))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Manufacturer entity) throws SQLException {
        ps.setString(1, entity.getName());
    }

    public Optional<Manufacturer> getByName(String name) throws DaoException {
        return super.getByField(SQL_SELECT_BY_NAME, name).stream().findFirst();
    }
}
