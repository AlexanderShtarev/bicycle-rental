package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Category;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryDao extends AbstractDao<Category> {
    private static CategoryDao instance;

    private static final String SQL_SELECT_CATEGORY = "SELECT category.id, category.name FROM category\n";

    private static final String SQL_SELECT_BY_ID = SQL_SELECT_CATEGORY + " WHERE id = ?\n";

    private static final String SQL_SELECT_BY_NAME = SQL_SELECT_CATEGORY + " WHERE name = ?\n";

    private static final String SQL_INSERT_CATEGORY = "INSERT INTO category(name) VALUES (?);\n";

    private static final String SQL_REMOVE_CATEGORY = "DELETE FROM category WHERE id = ?\n";

    public static CategoryDao getInstance() {
        if (instance == null)
            instance = new CategoryDao();
        return instance;
    }

    @Override
    public List<Category> getAll() throws DaoException {
        return super.getAll(SQL_SELECT_CATEGORY);
    }

    @Override
    public Optional<Category> getById(int id) throws DaoException {
        return super.getByField(SQL_SELECT_BY_ID, id).stream()
                .filter(Objects::nonNull).findFirst();
    }

    @Override
    public void insert(Connection con, Category item) throws DaoException {
        super.insert(con, SQL_INSERT_CATEGORY, item);
    }

    @Override
    public void update(Connection connection, Category entity) throws DaoException {
        throw new UnsupportedOperationException("Updating category not supported");
    }

    @Override
    public void remove(Connection con, int id) throws DaoException {
        super.deleteByField(con, SQL_REMOVE_CATEGORY, id);
    }

    @Override
    protected Category mapToEntity(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getInt("category.id"))
                .name(rs.getString("category.name"))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Category entity) throws SQLException {
        ps.setString(1, entity.getName());
    }

    public Optional<Category> getByName(String categoryName) throws DaoException {
        return super.getByField(SQL_SELECT_BY_NAME, categoryName).stream().filter(Objects::nonNull).findFirst();
    }

}
