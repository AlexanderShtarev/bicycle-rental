package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getProductsByCriteria(Criteria<? extends Product> criteria);

    Optional<Product> getSingleProductByCriteria(Criteria<? extends Product> criteria);

    List<Product> findAll();

    void update(Connection con, Product product);

    Boolean deleteById(Connection con, Long productId);

    Product persist(Connection con, Product product);
}
