package com.epam.jwd.criteria;

import com.epam.jwd.domain.Image;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductProducer;
import com.epam.jwd.domain.ProductType;

public class ProductCriteria extends Criteria<Product> {

    Long id;
    ProductProducer producer;
    String model;
    ProductType type;
    Image image;
    Double pricePerHour;

    public ProductCriteria(Criteria.Builder<? extends Criteria.Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public ProductProducer getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public ProductType getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<ProductCriteria.Builder> {

        Long id;
        ProductProducer producer;
        String model;
        ProductType type;
        Image image;
        Double pricePerHour;

        private Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder producer(ProductProducer producer) {
            this.producer = producer;
            return this;
        }

        public Builder type(ProductType type) {
            this.type = type;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Builder pricePerHour(Double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        @Override
        public ProductCriteria build() {
            ProductCriteria criteria = new ProductCriteria(this);
            criteria.id = id;
            criteria.producer = producer;
            criteria.model = model;
            criteria.type = type;
            criteria.image = image;
            criteria.pricePerHour = pricePerHour;

            return criteria;
        }

    }

}
