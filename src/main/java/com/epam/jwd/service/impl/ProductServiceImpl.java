package com.epam.jwd.service.impl;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.criteria.ProductProducerCriteria;
import com.epam.jwd.criteria.ProductTypeCriteria;
import com.epam.jwd.dao.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.service.ProductService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    TransactionHandler transactionHandler;

    ProductDao productDao;

    ProductProducerDao producerDao;

    ProductTypeDao typeDao;

    ImageDao imageDao;

    private static ProductServiceImpl instance;

    private ProductServiceImpl() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.DaoType.MYSQL);
            productDao = daoFactory.getProductDao();
            producerDao = daoFactory.getProductProducerDao();
            typeDao = daoFactory.getProductTypeDao();
            transactionHandler = new TransactionHandler();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productsCache =
                (List<Product>) ApplicationContext.APPLICATION_CONTEXT.getCache(Product.class);

        if (productsCache.isEmpty()) {
            productsCache = productDao.findAll();

            for (Product product : productsCache) {
                this.setProductDependencies(product);
                ApplicationContext.APPLICATION_CONTEXT.addToCache(product);
            }

        }
        return productsCache;
    }

    private void setProductDependencies(Product product) {
        /*setProductImage(product);*/
        setProductProducer(product);
        setProductType(product);
    }

    private void setProductType(Product product) {
        ProductType type = ApplicationContext.APPLICATION_CONTEXT.getCache(ProductType.class)
                .stream()
                .filter(value -> value.getId().equals(product.getType().getId()))
                .findFirst()
                .orElseGet(() -> {
                    ProductType productType = typeDao.findTypeByProductId();
                    ApplicationContext.APPLICATION_CONTEXT.addToCache(productType);
                    return productType;
                });
        product.setType(type);
    }

    private void setProductProducer(Product product) {
        ProductProducer producer = ApplicationContext.APPLICATION_CONTEXT.getCache(ProductProducer.class)
                .stream()
                .filter(value -> value.getId().equals(product.getProducer().getId()))
                .findFirst()
                .orElseGet(() -> {
                    ProductProducer productProducer = producerDao.findProducerByProductId();
                    ApplicationContext.APPLICATION_CONTEXT.addToCache(productProducer);
                    return productProducer;
                });
        product.setProducer(producer);
    }

    private void setProductImage(Product product) {
        Image image = ApplicationContext.APPLICATION_CONTEXT.getCache(Image.class).stream()
                .filter(value -> value.getId().equals(product.getImage().getId()))
                .findFirst()
                .orElseGet(() -> {
                    Image productImage = imageDao.findImageByProductId();
                    ApplicationContext.APPLICATION_CONTEXT.addToCache(productImage);
                    return productImage;
                });
        product.setImage(image);
    }

    @Override
    public List<Product> getAllProductsByCriteria(Criteria<? extends Product> productCriteria) {
        return this.filterProductByCriteria(productCriteria);
    }

    private List<Product> filterProductByCriteria(Criteria<? extends Product> productCriteria) {
        Collection<Product> productsCache = ApplicationContext.APPLICATION_CONTEXT.getCache(Product.class);
        ProductCriteria criteria = (ProductCriteria) productCriteria;
        return productsCache.stream().filter(product ->
                criteria.getId() == null || product.getId().equals(criteria.getId())
                        && criteria.getImage() == null || product.getImage().equals(criteria.getImage())
                        && criteria.getModel() == null || product.getModel().equals(criteria.getModel())
                        && criteria.getProducer() == null || product.getProducer().equals(criteria.getProducer())
                        && criteria.getType() == null || product.getType().equals(criteria.getType())
                        && criteria.getPricePerHour() == null || product.getPricePerHour().equals(criteria.getPricePerHour()))
                .collect(Collectors.toList());
    }

    @Override
    public Product getSingleProductByCriteria(Criteria<? extends Product> criteria) {
        List<Product> products = this.filterProductByCriteria(criteria);
        if (!products.isEmpty()) {
            return products.get(0);
        }
        Product product = getSingleProductFromDb((ProductCriteria) criteria);
        ApplicationContext.APPLICATION_CONTEXT.addToCache(product);
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        return transactionHandler.transactional(con -> {
            this.setProductDependencies(product);
            productDao.update(con, product);
            return true;
        });
    }

    private Product getSingleProductFromDb(ProductCriteria criteria) {
        return transactionHandler.transactional(con -> {
            Optional<Product> product = productDao.getSingleProductByCriteria(criteria);
            product.ifPresent(this::setProductDependencies);
            return product;
        }).orElseThrow(DaoException::new);
    }

    @Override
    public Product addProduct(Product product) {
        ProductTypeCriteria typeCriteria = ProductTypeCriteria.builder()
                .name(product.getType().getName())
                .build();
        ProductProducerCriteria producerCriteria = ProductProducerCriteria.builder()
                .name(product.getProducer().getName())
                .build();
        Optional<ProductType> type = typeDao.findSingleTypeByCriteria(typeCriteria);
        Optional<ProductProducer> producer = producerDao.findSingleProducerByCriteria(producerCriteria);
        //Optional<Image> image = imageDao.findSingleImageByCriteria();
        return transactionHandler.transactional(con -> {
            if (type.isEmpty()) {
                ProductType productType = typeDao.persist(con, product.getType());
                product.setType(productType);
            }

            if (producer.isEmpty()) {
                ProductProducer productProducer = producerDao.persist(con, product.getProducer());
                product.setProducer(productProducer);
            }

            //if (image.isEmpty()) {
            //    Image productImage = imageDao.persist(con, product.getImage());
            //    product.setImage(productImage);
            //}

            return productDao.persist(con, product);
        });
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return transactionHandler.transactional(con ->
                productDao.deleteById(con, productId));
    }


}
