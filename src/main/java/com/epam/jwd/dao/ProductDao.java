package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {

    List<Product> findAll(Connection con) throws DaoException;

    List<Product> findByCriteria(Connection con, Criteria<? extends Product> criteria) throws DaoException;

    void add(Connection con, Product product) throws DaoException;

    void update(Connection con, Product product) throws DaoException;

    void delete(Connection con, Long productId) throws DaoException;

}