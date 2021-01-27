package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductDao;
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

public class MySqlProductDao extends GenericDao<Product> implements ProductDao {
    private static MySqlProductDao instance;

    private static final String SQL_GET_ALL_PRODUCTS =
            "SELECT product.id, product.model, product.price_per_hour,\n" +
                    "       type.id, type.name, producer.id, producer.name,\n" +
                    "       images.id, images.title, images.image_link\n" +
                    "FROM product\n" +
                    "JOIN images ON images.id = product.image_id\n" +
                    "JOIN producer ON producer.id = product.producer_id\n" +
                    "JOIN type ON type.id = product.type_id";

    private static final String SQL_ADD_PRODUCT =
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
    protected Product mapToEntity(ResultSet rs) throws SQLException {
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
    protected void mapFromEntity(PreparedStatement ps, Product product) throws SQLException {
        ps.setString(1, product.getModel());
        ps.setDouble(2, product.getPricePerHour());
        ps.setLong(3, product.getProducer().getId());
        ps.setLong(4, product.getType().getId());
        ps.setLong(5, product.getImage().getId());
    }

    @Override
    public List<Product> findAll(Connection con) throws DaoException {
        return null;
    }

    @Override
    public List<Product> findByCriteria(Connection con, Criteria<? extends Product> criteria) throws DaoException {
        return null;
    }

    @Override
    public void add(Connection con, Product product) throws DaoException {

    }

    @Override
    public void update(Connection con, Product product) throws DaoException {

    }

    @Override
    public void delete(Connection con, Long productId) throws DaoException {

    }
}
