package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductDao;
import com.epam.jwd.dao.builder.ProductQueryBuilder;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.constant.ImageFieldsConstant;
import com.epam.jwd.dao.constant.ProductFieldsConstant;
import com.epam.jwd.dao.constant.ProductProducerFieldsConstant;
import com.epam.jwd.dao.constant.ProductTypeFieldsConstant;
import com.epam.jwd.domain.Image;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl extends GenericDao<Product> implements ProductDao {
    private static ProductDao instance;
    QueryBuilder builder = ProductQueryBuilder.PRODUCT_QUERY_BUILDER;

    private static final String SQL_SELECT_ALL_PRODUCTS =
            "SELECT product.id, product.model, product.price_per_hour,\n" +
                    "       product.type_id, product.producer_id,\n" +
                    "       product.image_id " +
                    "FROM product\n";/* +
                    "JOIN images ON images.id = product.image_id\n" +
                    "JOIN producer ON producer.id = product.producer_id\n" +
                    "JOIN type ON type.id = product.type_id";*/

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

    public static ProductDao getInstance() {
        if (instance == null)
            instance = new ProductDaoImpl();
        return instance;
    }

    @Override
    protected Product parseResultSet(ResultSet rs) throws DaoException, SQLException {
        return Product.builder()
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

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, Product product) throws DaoException, SQLException {
        ps.setString(1, product.getModel());
        ps.setDouble(2, product.getPricePerHour());
        ps.setLong(3, product.getProducer().getId());
        ps.setLong(4, product.getType().getId());
        ps.setLong(5, product.getImage().getId());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, Product product) throws DaoException, SQLException {
        ps.setString(1, product.getModel());
        ps.setDouble(2, product.getPricePerHour());
        ps.setLong(3, product.getProducer().getId());
        ps.setLong(4, product.getType().getId());
        ps.setLong(5, product.getImage().getId());
        ps.setLong(6, product.getId());
    }


    @Override
    public List<Product> getProductsByCriteria(Criteria<? extends Product> criteria) {
        return null;
    }

    @Override
    public Optional<Product> getSingleProductByCriteria(Criteria<? extends Product> criteria) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Connection con, Product product) {

    }

    @Override
    public Boolean deleteById(Connection con, Long productId) {
        return null;
    }

    @Override
    public Product persist(Connection con, Product product) {
        return null;
    }
}
