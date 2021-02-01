package com.epam.jwd.criteria;

import com.epam.jwd.domain.ProductType;

public class ProductTypeCriteria extends Criteria<ProductType> {

    Long id;
    String name;

    public ProductTypeCriteria(Criteria.Builder<? extends Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProductTypeCriteria.Builder builder() {
        return new ProductTypeCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<ProductTypeCriteria.Builder> {

        Long id;
        String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public ProductTypeCriteria build() {
            ProductTypeCriteria criteria = new ProductTypeCriteria(this);
            criteria.id = id;
            criteria.name = name;
            return criteria;
        }

    }

}
