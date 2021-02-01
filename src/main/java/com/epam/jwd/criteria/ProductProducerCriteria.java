package com.epam.jwd.criteria;

import com.epam.jwd.domain.ProductProducer;

public class ProductProducerCriteria extends Criteria<ProductProducer> {

    Long id;
    String name;

    public ProductProducerCriteria(Criteria.Builder<? extends ProductProducerCriteria.Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProductProducerCriteria.Builder builder() {
        return new ProductProducerCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<ProductProducerCriteria.Builder> {

        Long id;
        String name;

        public ProductProducerCriteria.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductProducerCriteria.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public ProductProducerCriteria build() {
            ProductProducerCriteria criteria = new ProductProducerCriteria(this);
            criteria.id = id;
            criteria.name = name;
            return criteria;
        }

    }
}
