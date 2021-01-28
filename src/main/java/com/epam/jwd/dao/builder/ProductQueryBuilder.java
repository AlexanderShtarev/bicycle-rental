package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.dao.constant.ImageFieldsConstant;
import com.epam.jwd.dao.constant.ProductFieldsConstant;
import com.epam.jwd.dao.constant.ProductProducerFieldsConstant;
import com.epam.jwd.dao.constant.ProductTypeFieldsConstant;
import com.epam.jwd.domain.Entity;

public class ProductQueryBuilder extends QueryBuilder{
    public static final ProductQueryBuilder PRODUCT_QUERY_BUILDER = new ProductQueryBuilder();

    private ProductQueryBuilder() {
    }

    @Override
    public String createQuery(Criteria<? extends Entity> criteria, String sqlGetAllProducts) {
        StringBuffer parameters = new StringBuffer();

        if (!(criteria instanceof ProductCriteria)) {
            throw new RuntimeException("incomparable type of criteria");
        }

        ProductCriteria prCriteria = (ProductCriteria) criteria;

        if (null != prCriteria.getId()) {
            appendValue(parameters, ProductFieldsConstant.PRODUCT_ID, prCriteria.getId());
        }

        if (prCriteria.getPricePerHour() != null) {
            appendValue(parameters, ProductFieldsConstant.PRODUCT_PRICE_PER_HOUR, prCriteria.getPricePerHour());
        }

        if (prCriteria.getModel() != null) {
            appendValue(parameters, ProductFieldsConstant.PRODUCT_MODEL, prCriteria.getPricePerHour());
        }

        if (prCriteria.getProducer() != null) {
            if (prCriteria.getProducer().getId() != null) {
                appendValue(parameters, ProductProducerFieldsConstant.PRODUCER_ID, prCriteria.getProducer().getId());
            }
            if (prCriteria.getProducer().getName() != null) {
                appendValue(parameters, ProductProducerFieldsConstant.PRODUCER_NAME, prCriteria.getProducer().getName());
            }
        }

        if (prCriteria.getType() != null) {
            if (prCriteria.getType().getId() != null) {
                appendValue(parameters, ProductTypeFieldsConstant.TYPE_ID, prCriteria.getType().getId());
            }
            if (prCriteria.getType().getName() != null) {
                appendValue(parameters, ProductTypeFieldsConstant.TYPE_NAME, prCriteria.getType().getName());
            }
        }

        if (prCriteria.getImage() != null) {
            if (prCriteria.getImage().getId() != null) {
                appendValue(parameters, ImageFieldsConstant.IMAGE_ID, prCriteria.getImage().getId());
            }
            if (prCriteria.getImage().getTitle() != null) {
                appendValue(parameters, ImageFieldsConstant.IMAGE_TITLE, prCriteria.getImage().getTitle());
            }
            if (prCriteria.getImage().getImageLink() != null) {
                appendValue(parameters, ImageFieldsConstant.IMAGE_IMAGE_LINK, prCriteria.getImage().getImageLink());
            }
        }

        return sqlGetAllProducts + " WHERE " + toStatement(parameters);
    }

}
