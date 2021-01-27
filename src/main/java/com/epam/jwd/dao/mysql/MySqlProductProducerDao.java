package com.epam.jwd.dao.mysql;

import com.epam.jwd.dao.GenericDao;
import com.epam.jwd.dao.ProductProducerDao;
import com.epam.jwd.dao.constant.ProductProducerFieldsConstant;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlProductProducerDao extends GenericDao<ProductProducer> implements ProductProducerDao {
    private static MySqlProductProducerDao instance;

    private static final String SQL_FIND_ALL_PRODUCERS =
            "";

    private static final String SQL_FIND_PRODUCER_BY_ID =
            SQL_FIND_ALL_PRODUCERS + "";

    private static final String SQL_FIND_PRODUCER_BY_NAME =
            SQL_FIND_ALL_PRODUCERS + "";

    private static final String SQL_ADD_PRODUCER =
            "";

    private static final String SQL_UPDATE_PRODUCER =
            "";

    private static final String SQL_DELETE_PRODUCER =
            "";

    public static MySqlProductProducerDao getInstance() {
        if (instance == null)
            instance = new MySqlProductProducerDao();
        return instance;
    }

    @Override
    protected ProductProducer mapToEntity(ResultSet rs) throws SQLException {

        return ProductProducer.builder()
                .id(rs.getLong(ProductProducerFieldsConstant.PRODUCER_ID))
                .name(rs.getString(ProductProducerFieldsConstant.PRODUCER_NAME))
                .build();

    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, ProductProducer producer) throws SQLException {

        ps.setString(1, producer.getName());

    }

    @Override
    public List<ProductProducer> findAll(Connection con) throws DaoException {
        return null;
    }

    @Override
    public ProductProducer findById(Connection con, Integer producerId) throws DaoException {
        return null;
    }

    @Override
    public ProductProducer findByName(Connection con, String producerName) throws DaoException {
        return null;
    }

    @Override
    public void add(Connection con, ProductProducer producer) throws DaoException {

    }

    @Override
    public void update(Connection con, ProductProducer producer) throws DaoException {

    }

    @Override
    public void delete(Connection con, Integer producerId) throws DaoException {

    }

}
