package com.epam.jwd.service;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.ServiceException;

import java.util.List;

public interface ProductService {

    List<Product> getFilteredProducts(ProductSearchBean bean) throws ServiceException;

    Product getProductById(int id) throws ServiceException;

    List<Manufacturer> getAllManufacturers() throws ServiceException;

    List<Category> getAllCategories() throws ServiceException;

    void insertProduct(Product product) throws ServiceException;

    void updateProduct(Product product) throws ServiceException;

    void removeProduct(int id) throws ServiceException;

    List<Product> getAllProducts() throws ServiceException;

    void removeCategory(int categoryId) throws ServiceException;

    void insertCategory(Category category) throws ServiceException;

    void insertManufacturer(Manufacturer manufacturer) throws ServiceException;

    void removeManufacturer(int manufacturerId) throws ServiceException;

    Category getCategoryByName(String categoryName) throws ServiceException;

    Manufacturer getManufacturerByName(String name) throws ServiceException;
}
