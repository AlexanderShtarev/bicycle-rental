package com.epam.jwd.controller.builder;

import com.epam.jwd.bean.ProductSearchBean;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.domain.Category;
import com.epam.jwd.domain.Manufacturer;
import com.epam.jwd.domain.Product;
import com.epam.jwd.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBuilder.class);

    public static ProductSearchBean getProductFilterFromRequest(HttpServletRequest request,
                                                                int defaultLimit,
                                                                int defaultPage) {

        LOGGER.info("Getting product filter from request");

        String name = request.getParameter(RequestConstant.NAME);
        String priceFrom = request.getParameter(RequestConstant.PRICE_FROM);
        String priceTo = request.getParameter(RequestConstant.PRICE_TO);
        String[] categories = request.getParameterValues(RequestConstant.CATEGORIES);
        String[] manufacturers = request.getParameterValues(RequestConstant.MANUFACTURERS);
        String sortedBy = request.getParameter(RequestConstant.SORT_BY);
        String page = request.getParameter(RequestConstant.PAGE);
        String size = request.getParameter(RequestConstant.SIZE);
        String[] isDescending = request.getParameterValues(RequestConstant.IS_DESCENDING);

        return setFilterFields(name, priceFrom, priceTo, categories, manufacturers,
                sortedBy, page, size, isDescending, defaultLimit, defaultPage);
    }

    private static ProductSearchBean setFilterFields(
            String name, String priceFrom, String priceTo, String[] categories, String[] manufacturers, String sortedBy,
            String page, String size, String[] isDescending, int defaultLimit, int defaultPage) {

        LOGGER.info("Setting product filter fields");

        ProductSearchBean bean = new ProductSearchBean();
        if (categories != null && categories.length != 0) {
            List<Integer> categoriesId = new ArrayList<>();
            for (String category : categories) {
                if (Validator.checkPositiveInteger(category).isEmpty()) {
                    categoriesId.add(Integer.parseInt(category));
                }
            }
            bean.setCategories(categoriesId);
        }

        if (manufacturers != null && manufacturers.length != 0) {
            List<Integer> manufacturersId = new ArrayList<>();
            for (String manufacturer : manufacturers) {
                if (Validator.checkPositiveInteger(manufacturer).isEmpty()) {
                    manufacturersId.add(Integer.parseInt(manufacturer));
                }
            }
            bean.setManufacturers(manufacturersId);
        }

        if (isDescending != null) {
            bean.setDescending(true);
        }

        if (Validator.checkPositiveInteger(page).isEmpty()) {
            bean.setPage(Integer.parseInt(page));
        } else {
            bean.setPage(defaultPage);
        }

        if (Validator.checkPositiveInteger(size).isEmpty()) {
            bean.setLimit(Integer.parseInt(size));
        } else {
            bean.setLimit(defaultLimit);
        }

        if (Validator.checkPositiveInteger(priceFrom).isEmpty()) {
            bean.setPriceFrom(new BigDecimal(priceFrom));
        }

        if (Validator.checkPositiveInteger((priceTo)).isEmpty()) {
            bean.setPriceTo(new BigDecimal(priceTo));
        }

        bean.setName(name);
        bean.setSortedBy(sortedBy);
        bean.setOffset((bean.getPage() - 1) * bean.getLimit());

        return bean;
    }

    public static Product getProductFromRequest(HttpServletRequest request) throws NumberFormatException {

        LOGGER.info("Getting product from request");

        String name = request.getParameter(RequestConstant.NAME);
        String price = request.getParameter(RequestConstant.PRICE);
        String description = request.getParameter(RequestConstant.DESCRIPTION);
        int manufacturerId = Integer.parseInt(request.getParameter(RequestConstant.MANUFACTURER_ID));
        int categoryId = Integer.parseInt(request.getParameter(RequestConstant.CATEGORY_ID));

        return Product.builder()
                .name(name)
                .price(new BigDecimal(price))
                .category(Category.builder().id(categoryId).build())
                .manufacturer(Manufacturer.builder().id(manufacturerId).build())
                .description(description)
                .build();

    }

    public static List<String> buildProductForUpdate(HttpServletRequest request, Product product) {
        LOGGER.info("Building product for update");

        String price = request.getParameter(RequestConstant.PRICE);
        String name = request.getParameter(RequestConstant.NAME);
        String manufacturerId = request.getParameter(RequestConstant.MANUFACTURER_ID);
        String categoryId = request.getParameter(RequestConstant.CATEGORY_ID);

        List<java.lang.String> errors = new ArrayList<>();
        Validator.checkPositiveDouble(price).ifPresent(errors::add);
        Validator.checkString(name).ifPresent(errors::add);
        Validator.checkPositiveInteger(manufacturerId).ifPresent(errors::add);
        Validator.checkPositiveInteger(categoryId).ifPresent(errors::add);

        if (errors.isEmpty()) {
            product.setManufacturer(Manufacturer.builder().id(Integer.valueOf(manufacturerId)).build());
            product.setCategory(Category.builder().id(Integer.valueOf(categoryId)).build());
            product.setPrice(new BigDecimal(price));
            product.setName(name);
        }
        return errors;
    }

}
