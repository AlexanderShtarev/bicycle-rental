package com.epam.jwd.validator;

import com.epam.jwd.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator extends Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductValidator.class);

    public static List<String> checkProduct(Product product) {
        LOGGER.info("Product validation method has been started");
        List<String> errors = new ArrayList<>();

        if (product != null) {
            checkPositiveDouble(product.getPrice().toString()).ifPresent(errors::add);
            checkString(product.getName()).ifPresent(errors::add);
            checkString(product.getDescription());
        }

        return errors;
    }

}
