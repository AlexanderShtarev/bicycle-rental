package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Entity;

public abstract class QueryBuilder {

    public abstract String createQuery(Criteria<? extends Entity> criteria);

    protected void appendValue(StringBuffer parameters, String paramSql, Object value){
    }

    protected StringBuffer toStatement(StringBuffer parameters) {
        return null;
    }

}
