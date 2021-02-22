package com.epam.jwd.dao.impl;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.dao.ProductDao;
import com.epam.jwd.dao.builder.SelectQueryBuilder;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.SelectBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);
    private static final int ID_PARAMETER_INDEX = 6;
    private static ProductDaoImpl instance = null;

    private static final String SQL_GET_PRODUCTS =
            "SELECT product.id, product.category_id, product.manufacturer_id, product.name,\n" +
                    "product.price, product.description FROM product\n";

    private static final String SQL_GET_BY_ID =
            SQL_GET_PRODUCTS + "WHERE id = ?\n";

    private static final String SQL_INSERT_PRODUCT =
            "INSERT INTO product(category_id, manufacturer_id, name, price, description) \n" +
                    "VALUES (?, ?, ?, ?, ?)\n";

    private static final String SQL_UPDATE_PRODUCT =
            "UPDATE product SET category_id = ?, manufacturer_id = ?, name = ?, price = ?, description = ?\n" +
                    "WHERE product.id = ?";

    private static final String SQL_REMOVE_PRODUCT = "DELETE FROM product WHERE product.id = ?";

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Product> getAll() throws DaoException {
        return super.getAll(SQL_GET_PRODUCTS);
    }

    @Override
    public Optional<Product> getById(int id) throws DaoException {
        return super.getByField(SQL_GET_BY_ID, id).stream()
                .filter(Objects::nonNull).findFirst();
    }

    @Override
    public void insert(Connection con, Product item) throws DaoException {
        super.insert(con, SQL_INSERT_PRODUCT, item);
    }

    @Override
    public void update(Connection con, Product item) throws DaoException {
        super.updateByField(con, SQL_UPDATE_PRODUCT, item, ID_PARAMETER_INDEX, item.getId());
    }

    @Override
    public void remove(Connection con, int id) throws DaoException {
        super.deleteByField(con, SQL_REMOVE_PRODUCT, id);
    }

    @Override
    protected Product mapToEntity(ResultSet rs) throws SQLException {
        return Product.builder()
                .id(rs.getInt("product.id"))
                .category(Category.builder()
                        .id(rs.getInt("product.category_id"))
                        .build())
                .manufacturer(Manufacturer.builder()
                        .id(rs.getInt("product.manufacturer_id"))
                        .build())
                .name(rs.getString("product.name"))
                .price(rs.getBigDecimal("product.price"))
                .description(rs.getString("product.description"))
                .build();
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Product product) throws SQLException {
        ps.setInt(1, product.getCategory().getId());
        ps.setInt(2, product.getManufacturer().getId());
        ps.setString(3, product.getName());
        ps.setBigDecimal(4, product.getPrice());
        ps.setString(5, product.getDescription());
    }

    @Override
    public List<Product> getFilteredProducts(ProductSearchBean bean) throws DaoException {
        Connection connection = this.getConnection();
        List<Product> products = new ArrayList<>();
        SelectQueryBuilder.Builder builder = new SelectQueryBuilder.Builder(connection);
        try {
            builder.select("*").from("product");
            setConditions(bean, builder);
        } catch (SelectBuilderException e) {
            LOGGER.warn("Parameters are null", e);
            throw new DaoException(e);
        }
        setSelectedRange(bean, builder);
        SelectQueryBuilder creator = builder.build();
        try (PreparedStatement statement = creator.createStatement()) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                products.add(mapToEntity(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.close(connection);
        }
        return products;
    }

    private void setConditions(ProductSearchBean bean, SelectQueryBuilder.Builder builder) throws SelectBuilderException {
        LOGGER.trace("Setting conditions for select query");
        if (bean.getName() != null && !bean.getName().isEmpty()) {
            builder.whereEquals("product.name", bean.getName());
        }
        if (bean.getPriceFrom() != null) {
            builder.whereGreaterEquals("product.price", bean.getPriceFrom());
        }
        if (bean.getPriceTo() != null) {
            builder.whereLessEquals("product.price", bean.getPriceTo());
        }
        if (bean.getCategories() != null) {
            builder.whereIn("product.category_id", bean.getCategories());
        }
        if (bean.getManufacturers() != null) {
            builder.whereIn("product.manufacturer_id", bean.getManufacturers());
        }
        if (bean.getSortedBy() != null) {
            builder.orderBy(bean.getSortedBy());
            if (bean.isDescending()) {
                builder.descendingOrder();
            }
        }
    }

    private void setSelectedRange(ProductSearchBean bean, SelectQueryBuilder.Builder builder) {
        LOGGER.trace("setting select range for select query");
        builder.limit(bean.getLimit());
        builder.setOffset(bean.getOffset());
    }
}
