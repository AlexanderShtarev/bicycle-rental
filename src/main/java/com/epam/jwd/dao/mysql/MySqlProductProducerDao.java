package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.domain.ProductProducer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlProductProducerDao extends GenericDao<ProductProducer> {

    private static final String SQL_GET_ALL_PRODUCERS =
            "";

    private static final String SQL_ADD_PRODUCER =
            "";

    private static final String SQL_UPDATE_PRODUCER =
            "";

    private static final String SQL_DELETE_PRODUCER =
            "";

    @Override
    protected ProductProducer mapToEntity(ResultSet rs) throws SQLException {

        return ProductProducer.builder()
                .id(rs.getLong("producer.id"))
                .name(rs.getString("producer.name"))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, ProductProducer producer) throws SQLException {

        ps.setString(1, producer.getName());

    }

}
