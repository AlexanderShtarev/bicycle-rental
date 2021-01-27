package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.*;
import sun.net.www.content.text.Generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlInventoryDao extends GenericDao<Inventory> {

    private static final String SQL_GET_ALL_INVENTORIES =
            "";

    private static final String SQL_ADD_INVENTORY =
            "";

    private static final String SQL_UPDATE_INVENTORY =
            "";

    private static final String SQL_DELETE_INVENTORY =
            "";

    @Override
    protected Inventory mapToEntity(ResultSet rs) throws SQLException {

        return Inventory.builder()
                .id(rs.getLong("inventory.id"))
                .product(Product.builder()
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
                        .build())
                .store(Store.builder()
                        .id(rs.getLong("store.id"))
                        .address(rs.getString("store.address"))
                        .phone(rs.getString("store.phone"))
                        .build())
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, Inventory inventory) throws SQLException {

        ps.setLong(1, inventory.getProduct().getId());
        ps.setLong(2, inventory.getStore().getId());

    }

}
