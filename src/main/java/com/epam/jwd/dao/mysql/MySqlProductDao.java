package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.ProductDao;
import com.epam.jwd.dao.builder.ProductQueryBuilder;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.constant.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlProductDao extends AbstractJDBCDao<Product, Long> implements ProductDao {
    private static MySqlProductDao instance;
    QueryBuilder builder = ProductQueryBuilder.PRODUCT_QUERY_BUILDER;

    private static final String SQL_SELECT_ALL_PRODUCTS =
            "SELECT product.id, product.model, product.price_per_hour,\n" +
                    "       product.type_id, type.name, product.producer_id, producer.name,\n" +
                    "       product.image_id, images.title, images.image_link\n" +
                    "FROM product\n" +
                    "JOIN images ON images.id = product.image_id\n" +
                    "JOIN producer ON producer.id = product.producer_id\n" +
                    "JOIN type ON type.id = product.type_id";

    private static final String SQL_CREATE_PRODUCT =
            "INSERT INTO product(model, price_per_hour, producer_id, type_id, image_id)\n" +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_PRODUCT =
            "UPDATE product\n" +
                    "SET model = ?, price_per_hour = ?, producer_id = ?, type_id = ?, image_id = ?;\n" +
                    "WHERE product.id = ?";

    private static final String SQL_DELETE_PRODUCT =
            "DELETE FROM product\n" +
                    "WHERE product.id = ?;";

    public static MySqlProductDao getInstance() {
        if (instance == null)
            instance = new MySqlProductDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_PRODUCTS;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_PRODUCT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_PRODUCT;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_PRODUCT;
    }

    @Override
    protected String getCountQuery() {
        return null;
    }

    @Override
    protected List<Product> parseResultSet(ResultSet rs) throws DaoException {
        List<Product> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Product product = Product.builder()
                        .id(rs.getLong(ProductFieldsConstant.PRODUCT_ID))
                        .model(rs.getString(ProductFieldsConstant.PRODUCT_MODEL))
                        .pricePerHour(rs.getDouble(ProductFieldsConstant.PRODUCT_PRICE_PER_HOUR))
                        .producer(ProductProducer.builder()
                                .id(rs.getLong(ProductFieldsConstant.PRODUCT_PRODUCER_ID))
                                .name(rs.getString(ProductProducerFieldsConstant.PRODUCER_NAME))
                                .build())
                        .type(ProductType.builder()
                                .id(rs.getLong(ProductFieldsConstant.PRODUCT_TYPE_ID))
                                .name(rs.getString(ProductTypeFieldsConstant.TYPE_NAME))
                                .build())
                        .image(Image.builder()
                                .id(rs.getLong(ProductFieldsConstant.PRODUCT_IMAGE_ID))
                                .title(rs.getString(ImageFieldsConstant.IMAGE_TITLE))
                                .imageLink(rs.getString(ImageFieldsConstant.IMAGE_IMAGE_LINK))
                                .build())
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Product product) throws DaoException {
        try {
            ps.setString(1, product.getModel());
            ps.setDouble(2, product.getPricePerHour());
            ps.setLong(3, product.getProducer().getId());
            ps.setLong(4, product.getType().getId());
            ps.setLong(5, product.getImage().getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Product product) throws DaoException {
        try {
            ps.setString(1, product.getModel());
            ps.setDouble(2, product.getPricePerHour());
            ps.setLong(3, product.getProducer().getId());
            ps.setLong(4, product.getType().getId());
            ps.setLong(5, product.getImage().getId());
            ps.setLong(6, product.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> getAll(Connection con) throws DaoException {
        return super.getAll(con);
    }

    @Override
    public List<Product> getByCriteria(Connection con, Criteria<? extends Product> criteria) throws DaoException {
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public Long add(Connection con, Product product) throws DaoException {
        Long id = super.add(con, product);
        product.setId(id);
        return id;
    }

    @Override
    public void update(Connection con, Product product) throws DaoException {
        super.update(con, product);
    }

    @Override
    public void delete(Connection con, Long productId) throws DaoException {
        super.delete(con, productId);
    }

}
