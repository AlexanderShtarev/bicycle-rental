package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.Image;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.domain.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlProductDao extends GenericDao<Product> {

    private static final String SQL_GET_ALL_PRODUCTS =
            "";

    private static final String SQL_ADD_PRODUCT =
            "";

    private static final String SQL_UPDATE_PRODUCT =
            "";

    private static final String SQL_DELETE_PRODUCT =
            "";


    @Override
    protected Product mapToEntity(ResultSet rs) throws SQLException {

        return Product.builder()
                .id(rs.getLong("product.id"))
                .model(rs.getString("product.model"))
                .pricePerHour(rs.getDouble("product.price_per_hour"))
                .producer(ProductProducer.builder()
                        .id(rs.getLong("producer.id"))
                        .name(rs.getString("producer.name"))
                        .build())
                .type(ProductType.builder()
                        .id(rs.getLong("type.id"))
                        .name(rs.getString("type.name"))
                        .build())
                .image(Image.builder()
                        .id(rs.getLong("image.id"))
                        .title(rs.getString("image.title"))
                        .imageLink(rs.getString("image.image_link"))
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

}
