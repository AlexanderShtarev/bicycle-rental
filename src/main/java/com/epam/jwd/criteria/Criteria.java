package com.epam.jwd.criteria;

import com.epam.jwd.domain.Entity;

public abstract class Criteria<T extends Entity> {

    public Criteria(Builder<? extends Builder> builder) {
    }

    protected static abstract class Builder<T extends Builder<T>> {
        public abstract Criteria build();
    }

}
