package com.epam.jwd.dao;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> getFilteredProducts(ProductSearchBean bean) throws DaoException;

}
