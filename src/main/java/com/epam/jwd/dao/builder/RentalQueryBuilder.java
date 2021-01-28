package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RentalCriteria;
import com.epam.jwd.dao.constant.RentalFieldsConstant;
import com.epam.jwd.domain.Entity;

public class RentalQueryBuilder extends QueryBuilder {
    public static final RentalQueryBuilder RENTAL_QUERY_BUILDER = new RentalQueryBuilder();

    private RentalQueryBuilder() {
    }

    @Override
    public String createQuery(Criteria<? extends Entity> criteria, String sqlGetAllRentals) {
        StringBuffer parameters = new StringBuffer();

        if (!(criteria instanceof RentalCriteria)) {
            throw new RuntimeException("incomparable type of criteria");
        }

        RentalCriteria rentalCriteria = (RentalCriteria) criteria;

        if (null != rentalCriteria.getId()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_ID, rentalCriteria.getId());
        }

        if (null != rentalCriteria.getInventory().getId()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_INVENTORY_ID, rentalCriteria.getInventory().getId());
        }

        if (null != rentalCriteria.getProductQty()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_PRODUCT_QTY, rentalCriteria.getProductQty());
        }

        if (null != rentalCriteria.getUser().getId()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_USER_ID, rentalCriteria.getUser().getId());
        }

        if (null != rentalCriteria.getRentalDate()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_RENTAL_DATE, rentalCriteria.getRentalDate());
        }

        if (null != rentalCriteria.getReturnDate()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_RETURN_DATE, rentalCriteria.getReturnDate());
        }

        if (null != rentalCriteria.getTotal()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_TOTAL, rentalCriteria.getTotal());
        }

        if (null != rentalCriteria.getStatus().getId()) {
            appendValue(parameters, RentalFieldsConstant.RENTAL_PRODUCT_QTY, rentalCriteria.getStatus().getId());
        }

        return sqlGetAllRentals + " WHERE " + toStatement(parameters);
    }

}
