package com.epam.jwd.service;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.domain.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    boolean deleteProduct(Long productId);

    List<Product> getAllProductsByCriteria(Criteria<? extends Product> criteria);

    List<Product> getAllProducts();

    Product getSingleProductByCriteria(Criteria<? extends Product> criteria);

    boolean updateProduct(Product product);

}
