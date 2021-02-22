package com.epam.jwd.dao.builder;

import com.epam.jwd.exception.SelectBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SelectQueryBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectQueryBuilder.class);

    private Connection connection;
    private String query;
    private List<Object> parameters;

    private SelectQueryBuilder(Connection connection, String query, List<Object> parameters) {
        this.connection = connection;
        this.query = query;
        this.parameters = parameters;
    }

    public static class Builder {

        private List<String> columns = new ArrayList<>();
        private List<String> tables = new ArrayList<>();
        private List<String> conditions = new ArrayList<>();
        private List<String> sorts = new ArrayList<>();
        private Integer limit;
        private Integer offset;
        private boolean isDescending;

        private List<Object> parameters = new ArrayList<>();
        private Connection connection;

        public Builder(Connection connection) {
            this.connection = connection;
        }

        public SelectQueryBuilder build() {
            String query = buildParametrizedQuery();
            return new SelectQueryBuilder(connection, query, parameters);
        }

        /**
         * Sets columns of the query
         */
        public Builder select(String firstColumn, String... otherColumns) throws SelectBuilderException {
            checkParametersForNull(firstColumn);
            columns.add(firstColumn);
            columns.addAll(Arrays.asList(otherColumns));
            return this;
        }

        /**
         * Sets tables of the query
         */
        public Builder from(String firstTable, String... otherTables) throws SelectBuilderException {
            checkParametersForNull(firstTable);
            tables.add(firstTable);
            tables.addAll(Arrays.asList(otherTables));
            return this;
        }

        /**
         * Sets 'equals' condition inside where statement of the query
         */
        public Builder whereEquals(String column, Object value) throws SelectBuilderException {
            checkParametersForNull(column, value);
            String sqlPart = column + "=?";

            conditions.add(sqlPart);
            parameters.add(value);
            return this;
        }

        /**
         * Sets 'less or equals' condition inside where statement of the query
         */
        public Builder whereLessEquals(String column, Object value) throws SelectBuilderException {
            checkParametersForNull(column, value);
            String sqlPart = column + "<=?";
            conditions.add(sqlPart);
            parameters.add(value);
            return this;
        }

        /**
         * Sets 'greater or equals' condition inside where statement of the
         * query
         */
        public Builder whereGreaterEquals(String column, Object value) throws SelectBuilderException {
            checkParametersForNull(column, value);
            String sqlPart = column + ">=?";
            conditions.add(sqlPart);
            parameters.add(value);
            return this;
        }

        /**
         * Sets 'in' condition inside where statement of the query
         */
        public Builder whereIn(String column, List<?> values) throws SelectBuilderException {
            checkParametersForNull(column, values);
            checkParametersForEmpty(values);
            StringBuilder sqlPart = new StringBuilder();

            Iterator<?> iterator = values.iterator();
            sqlPart.append(column).append(" IN (?");
            parameters.add(iterator.next());

            while (iterator.hasNext()) {
                sqlPart.append(",?");
                parameters.add(iterator.next());
            }
            sqlPart.append(")");
            conditions.add(sqlPart.toString());
            return this;
        }

        /**
         * Sets columns by witch query result will be ordered
         */
        public Builder orderBy(String firstValue, String... values) throws SelectBuilderException {
            checkParametersForNull(values);
            sorts.add(firstValue);
            sorts.addAll(Arrays.asList(values));
            return this;
        }

        /**
         * Sets result limit
         */
        public Builder limit(Integer size) {
            limit = size;
            return this;
        }

        /**
         * Sets descending order of the sorting
         */
        public Builder descendingOrder() {
            isDescending = true;
            return this;
        }

        /**
         * Sets result offset
         */
        public Builder setOffset(Integer offsetParameter) {
            offset = offsetParameter;
            return this;
        }

        private void checkParametersForNull(Object... parameters) throws SelectBuilderException {
            for (Object parameter : parameters) {
                if (parameter == null) {
                    LOGGER.warn("Null sql buider parameter");
                    throw new SelectBuilderException("Null sql builder parameter");
                }
            }
        }

        /**
         * Checks if parameters are empty
         */
        private void checkParametersForEmpty(List<?> parameters) throws SelectBuilderException {
            if (parameters.isEmpty()) {
                LOGGER.warn("List of parameters is empty");
                throw new SelectBuilderException("List of parameters is empty");
            }
        }

        /**
         * Builds the query
         */
        private String buildParametrizedQuery() {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(columns.get(0));
            for (int i = 1; i < columns.size(); i++) {
                sb.append(", ").append(columns.get(i));
            }
            sb.append(" FROM ");
            sb.append(tables.get(0));
            for (int i = 1; i < tables.size(); i++) {
                sb.append(", ").append(tables.get(i));
            }
            if (!conditions.isEmpty()) {
                sb.append(" WHERE ");
                sb.append(conditions.get(0));
                for (int i = 1; i < conditions.size(); i++) {
                    sb.append(" AND ").append(conditions.get(i));
                }
            }
            if (!sorts.isEmpty()) {
                sb.append(" ORDER BY ");
                sb.append(sorts.get(0));
                for (int i = 1; i < sorts.size(); i++) {
                    sb.append(",").append(sorts.get(i));
                }
                if (isDescending) {
                    sb.append(" DESC");
                }
            }
            if (limit != null) {
                sb.append(" LIMIT ");
                if (offset != null) {
                    sb.append(offset).append(", ");
                }
                sb.append(limit);
            }
            return sb.toString();
        }

    }

    /**
     * Creates PreparedStatement object
     */
    public PreparedStatement createStatement() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }

        return statement;
    }
}
