package com.epam.jwd.service.impl;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.dao.TransactionHandler;
import com.epam.jwd.dao.impl.CategoryDao;
import com.epam.jwd.dao.impl.ManufacturerDao;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.repository.ProductRepository;
import com.epam.jwd.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private static ProductServiceImpl instance;

    private final ProductRepository productRepository;
    private final CategoryDao categoryDao;
    private final ManufacturerDao manufacturerDao;

    private ProductServiceImpl() {
        this.productRepository = ProductRepository.getInstance();
        this.categoryDao = CategoryDao.getInstance();
        this.manufacturerDao = ManufacturerDao.getInstance();
    }

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Product> getFilteredProducts(ProductSearchBean bean) throws ServiceException {
        try {
            return productRepository.getFilteredProducts(bean);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting filtered products: ", e);
            throw new ServiceException("Dao provided exception while getting filtered products: ", e);
        }
    }

    @Override
    public Product getProductById(int id) throws ServiceException {
        try {
            return productRepository.getById(id).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting product by id: ", e);
            throw new ServiceException("Dao provided exception while getting product by id: ", e);
        }
    }

    @Override
    public List<Manufacturer> getAllManufacturers() throws ServiceException {
        try {
            return productRepository.getAllManufacturers();
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting all manufacturers: ", e);
            throw new ServiceException("Dao provided exception while getting all manufacturers: ", e);
        }
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        try {
            return productRepository.getAllCategories();
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting all categories: ", e);
            throw new ServiceException("Dao provided exception while getting all categories: ", e);
        }
    }

    @Override
    public void insertProduct(Product product) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    productRepository.insertProduct(con, product));
        } catch (Exception e) {
            LOGGER.error("Dao provided exception while inserting product: ", e);
            throw new ServiceException("Dao provided exception while inserting product: ", e);
        }
    }

    @Override
    public void updateProduct(Product product) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    productRepository.updateProduct(con, product));
        } catch (Exception e) {
            LOGGER.error("Dao provided exception while updating product: ", e);
            throw new ServiceException("Dao provided exception while updating product: ", e);
        }
    }

    @Override
    public void removeProduct(int id) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    productRepository.removeProduct(con, id));
        } catch (Exception e) {
            LOGGER.error("Dao provided exception while removing product: ", e);
            throw new ServiceException("Dao provided exception while removing product: ", e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        try {
            return productRepository.getAll();
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting all products: ", e);
            throw new ServiceException("Dao provided exception while getting all products: ", e);
        }
    }

    @Override
    public void removeCategory(int categoryId) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    categoryDao.remove(con, categoryId)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while removing category: ", e);
            throw new ServiceException("Dao provided exception while removing category: ", e);
        }
    }

    @Override
    public void insertCategory(Category category) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    categoryDao.insert(con, category)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while inserting category: ", e);
            throw new ServiceException("Dao provided exception while inserting category: ", e);
        }
    }

    @Override
    public void insertManufacturer(Manufacturer manufacturer) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    manufacturerDao.insert(con, manufacturer)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while inserting manufacturer: ", e);
            throw new ServiceException("Dao provided exception while inserting manufacturer: ", e);
        }
    }

    @Override
    public void removeManufacturer(int manufacturerId) throws ServiceException {
        try {
            TransactionHandler.runInTransaction(con ->
                    manufacturerDao.remove(con, manufacturerId)
            );
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while removing manufacturer: ", e);
            throw new ServiceException("Dao provided exception while removing manufacturer: ", e);
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) throws ServiceException {
        try {
            return categoryDao.getByName(categoryName).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting category by name: ", e);
            throw new ServiceException("Dao provided exception while getting category by name: ", e);
        }
    }

    @Override
    public Manufacturer getManufacturerByName(String name) throws ServiceException {
        try {
            return manufacturerDao.getByName(name).orElse(null);
        } catch (DaoException e) {
            LOGGER.error("Dao provided exception while getting manufacturer by name: ", e);
            throw new ServiceException("Dao provided exception while getting manufacturer by name: ", e);
        }
    }

}
