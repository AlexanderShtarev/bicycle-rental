package com.epam.jwd.service;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getFeaturedProducts();

    List<Product> getProductsByCriteria(Criteria<? extends Product> criteria);

    Product getProductById(Long id);

}
