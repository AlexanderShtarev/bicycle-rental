package com.epam.jwd.criteria;

import com.epam.jwd.domain.Inventory;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;

import java.util.Date;

public class RentalCriteria extends Criteria<Rental> {

    Long id;
    User user;
    Inventory inventory;
    Date rentalDate;
    Date returnDate;
    Integer productQty;
    Double total;
    RentalStatus status;

    public RentalCriteria(Criteria.Builder<? extends Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public Double getTotal() {
        return total;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public static RentalCriteria.Builder builder() {
        return new RentalCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<RentalCriteria.Builder> {

        Long id;
        User user;
        Inventory inventory;
        Date rentalDate;
        Date returnDate;
        Integer productQty;
        Double total;
        RentalStatus status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder inventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder rentalDate(Date rentalDate) {
            this.rentalDate = rentalDate;
            return this;
        }

        public Builder returnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder productQty(Integer productQty) {
            this.productQty = productQty;
            return this;
        }

        public Builder total(Double total) {
            this.total = total;
            return this;
        }

        public Builder status(RentalStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public RentalCriteria build() {
            RentalCriteria criteria = new RentalCriteria(this);
            criteria.id = id;
            criteria.inventory = inventory;
            criteria.productQty = productQty;
            criteria.rentalDate = rentalDate;
            criteria.returnDate = returnDate;
            criteria.status = status;
            criteria.total = total;
            criteria.user = user;
            return criteria;
        }

    }

}