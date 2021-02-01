package com.epam.jwd.service.impl;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.ProductTypeCriteria;
import com.epam.jwd.dao.ProductTypeDao;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductType;

import java.util.Optional;

public class ProductTypeServiceImpl {

    ProductTypeDao typeDao;

    private ProductType getProductTypeByProductId(Product product) {
        return ApplicationContext.APPLICATION_CONTEXT.getCache(ProductType.class)
                .stream()
                .filter(value -> value.getId().equals(product.getType().getId()))
                .findFirst()
                .orElseGet(() -> {
                    ProductType productType = typeDao.findTypeByProductId();
                    ApplicationContext.APPLICATION_CONTEXT.addToCache(productType);
                    return productType;
                });
    }

    public ProductType getProductTypeByCriteria(Criteria<? extends ProductType> typeCriteria) {
        ProductTypeCriteria criteria = (ProductTypeCriteria) typeCriteria;
        return ApplicationContext.APPLICATION_CONTEXT.getCache(ProductType.class)
                .stream()
                .filter(value ->
                        criteria.getId() == null || value.getId().equals(criteria.getId())
                                && criteria.getName() == null || value.getName().equals(criteria.getName()))
                .findFirst()
                .orElseGet(() -> {
                    Optional<ProductType> type = typeDao.findSingleTypeByCriteria(criteria);
                    type.ifPresent(ApplicationContext.APPLICATION_CONTEXT::addToCache);
                    return type.orElse(null);
                });
    }

    public void deleteAllBicyclesWithProductType(ProductType type) {

    }


}
