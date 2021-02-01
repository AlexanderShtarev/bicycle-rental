package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.NotEmpty;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Rental extends Entity implements Identified<Long> {

    @NotEmpty
    User user;

    @NotEmpty
    Map<Inventory, Integer> orderedProducts;

    @NotEmpty
    Date rentalDate;

    Date returnDate;

    @NotEmpty
    Double total;

    @NotEmpty
    RentalStatus status;

    public Rental() {
    }

    public Rental(Rental.Builder builder) {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id.equals(rental.id) &&
                user.equals(rental.user) &&
                inventory.equals(rental.inventory) &&
                rentalDate.equals(rental.rentalDate) &&
                Objects.equals(returnDate, rental.returnDate) &&
                productQty.equals(rental.productQty) &&
                total.equals(rental.total) &&
                status.equals(rental.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, inventory, rentalDate, returnDate, productQty, total, status);
    }

    public static Rental.Builder builder() {
        return new Rental.Builder();
    }

    public static class Builder {
        Long id;
        User user;
        Inventory inventory;
        Date rentalDate;
        Date returnDate;
        Integer productQty;
        Double total;
        RentalStatus status;

        private Builder() {
        }

        public Rental.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Rental.Builder user(User user) {
            this.user = user;
            return this;
        }

        public Rental.Builder inventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Rental.Builder rentalDate(Date rentalDate) {
            this.rentalDate = rentalDate;
            return this;
        }

        public Rental.Builder returnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Rental.Builder productQty(Integer productQty) {
            this.productQty = productQty;
            return this;
        }

        public Rental.Builder total(Double total) {
            this.total = total;
            return this;
        }

        public Rental.Builder status(RentalStatus status) {
            this.status = status;
            return this;
        }

        public Rental build() {
            Rental rental = new Rental(this);
            rental.id = id;
            rental.inventory = inventory;
            rental.status = status;
            rental.productQty = productQty;
            rental.rentalDate = rentalDate;
            rental.returnDate = returnDate;
            rental.total = total;
            rental.user = user;
            return rental;
        }

    }

}
