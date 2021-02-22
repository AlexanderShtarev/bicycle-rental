package com.epam.jwd.repository;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.dao.DaoFactory;
import com.epam.jwd.dao.ProductDao;
import com.epam.jwd.dao.impl.CategoryDao;
import com.epam.jwd.dao.impl.ManufacturerDao;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private static ProductRepository instance;

    private final CategoryDao categoryDAO;
    private final ManufacturerDao manufacturerDAO;
    private final ProductDao productDAO;

    public ProductRepository() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.categoryDAO = daoFactory.getCategoryDao();
        this.manufacturerDAO = daoFactory.getManufacturerDao();
        this.productDAO = daoFactory.getProductDao();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public List<Product> getAll() throws DaoException {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            this.setProductLinks(product);
        }
        return products;
    }

    public Optional<Product> getById(int id) throws DaoException {
        Optional<Product> product = productDAO.getById(id);
        if (product.isPresent()) {
            this.setProductLinks(product.get());
        }
        return product;
    }

    public List<Product> getFilteredProducts(ProductSearchBean bean) throws DaoException {
        List<Product> products = productDAO.getFilteredProducts(bean);
        for (Product product : products) {
            this.setProductLinks(product);
        }
        return products;
    }

    public List<Category> getAllCategories() throws DaoException {
        return categoryDAO.getAll();
    }

    public List<Manufacturer> getAllManufacturers() throws DaoException {
        return manufacturerDAO.getAll();
    }

    public void insertProduct(Connection con, Product product) throws DaoException {
        if (product.getManufacturer().getId() == null) {
            manufacturerDAO.insert(con, product.getManufacturer());
        }
        if (product.getCategory().getId() == null) {
            categoryDAO.insert(con, product.getCategory());
        }
        productDAO.insert(con, product);
    }

    public void updateProduct(Connection con, Product product) throws DaoException {
        productDAO.update(con, product);
    }

    public void removeProduct(Connection con, int id) throws DaoException {
        productDAO.remove(con, id);
    }

    public void setProductLinks(Product product) throws DaoException {
        Integer categoryId = product.getCategory().getId();
        Integer manufacturerId = product.getManufacturer().getId();
        categoryDAO.getById(categoryId).ifPresent(product::setCategory);
        manufacturerDAO.getById(manufacturerId).ifPresent(product::setManufacturer);
    }
}
