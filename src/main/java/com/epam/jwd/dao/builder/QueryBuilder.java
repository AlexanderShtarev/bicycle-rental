package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Entity;

public abstract class QueryBuilder {

    public abstract String createQuery(Criteria<? extends Entity> criteria, String parentGetAllSql);

    protected void appendValue(StringBuffer parameters, String paramSql, Object value){
        parameters
                .append(" ")
                .append(paramSql)
                .append(" = ")
                .append("'")
                .append(value)
                .append("'")
                .append(" AND");
    }

    protected StringBuffer toStatement(StringBuffer parameters) {
        int startIndex = parameters.lastIndexOf(" ");
        int endIndex = startIndex + "AND".length() + 1;
        parameters.delete(startIndex, endIndex);
        return parameters;
    }

}
