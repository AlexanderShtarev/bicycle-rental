package com.epam.jwd.converter.impl;

import com.epam.jwd.converter.Converter;
import com.epam.jwd.domain.Product;
import com.epam.jwd.dto.ProductDto;

public class ProductConverter implements Converter<Product, ProductDto> {
    public static final ProductConverter USER_CONVERTER = new ProductConverter();

    private ProductConverter() {
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto toDto(Product product) {
        return null;
    }

}
