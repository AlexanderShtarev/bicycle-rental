package com.epam.jwd.dao.mysql;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.AbstractJDBCDao;
import com.epam.jwd.dao.ProductProducerDao;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.constant.ProductProducerFieldsConstant;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlProductProducerDao extends AbstractJDBCDao<ProductProducer, Long> implements ProductProducerDao {
    private static MySqlProductProducerDao instance;
    private QueryBuilder builder;

    private static final String SQL_SELECT_ALL_PRODUCERS =
            "SELECT producer.id, producer.name FROM producer\n";

    private static final String SQL_CREATE_PRODUCER =
            "INSERT INTO producer(name)\n" +
                    "VALUES (?);";

    private static final String SQL_UPDATE_PRODUCER =
            "UPDATE producer\n" +
                    "SET producer = ?\n" +
                    "WHERE id = ?";

    private static final String SQL_DELETE_PRODUCER =
            "DELETE producer\n" +
                    "FROM producer\n" +
                    "WHERE id = ?";

    public static MySqlProductProducerDao getInstance() {
        if (instance == null)
            instance = new MySqlProductProducerDao();
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ALL_PRODUCERS;
    }

    @Override
    public String getCreateQuery() {
        return SQL_CREATE_PRODUCER;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_PRODUCER;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_PRODUCER;
    }

    @Override
    protected List<ProductProducer> parseResultSet(ResultSet rs) throws DaoException {
        List<ProductProducer> list = new LinkedList<>();
        try {
            while (rs.next()) {
                ProductProducer producer = ProductProducer.builder()
                        .id(rs.getLong(ProductProducerFieldsConstant.PRODUCER_ID))
                        .name(rs.getString(ProductProducerFieldsConstant.PRODUCER_NAME))
                        .build();
                list.add(producer);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement ps, ProductProducer producer) throws DaoException {
        try {
            ps.setString(1, producer.getName());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, ProductProducer producer) throws DaoException {
        try {
            ps.setString(1, producer.getName());
            ps.setLong(2, producer.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<ProductProducer> getAll(Connection con) throws DaoException {
        return super.getAll(con);
    }

    @Override
    public List<ProductProducer> getByCriteria(Connection con, Criteria<? extends ProductProducer> criteria) throws DaoException {
        return super.getByCriteria(con, criteria, builder);
    }

    @Override
    public Long add(Connection con, ProductProducer producer) throws DaoException {
        Long id = super.add(con, producer);
        producer.setId(id);
        return id;
    }

    @Override
    public void update(Connection con, ProductProducer producer) throws DaoException {
        super.update(con, producer);
    }

    @Override
    public void delete(Connection con, Long producerId) throws DaoException {
        super.delete(con, producerId);
    }

}
