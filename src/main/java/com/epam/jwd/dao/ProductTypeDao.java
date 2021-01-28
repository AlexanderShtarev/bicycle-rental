package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductTypeDao {

    List<ProductType> getAll(Connection con) throws DaoException;

    List<ProductType> getByCriteria(Connection con, Criteria<? extends ProductType> criteria) throws DaoException;

    Long add(Connection con, ProductType type) throws DaoException;

    void update(Connection con, ProductType type) throws DaoException;

    void delete(Connection con, Long typeId) throws DaoException;

}