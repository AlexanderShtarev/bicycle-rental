/*
package com.epam.jwd.dao.test;

import com.epam.jwd.domain.Entity;

import java.lang.reflect.Field;

public class SelectByCriteriaQuery extends QueryFactory {

    public static String build(Entity entity) {
        StringBuilder sql = new StringBuilder();
        sql.append("\nWHERE ");

        for (Field field : entity.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    appendValue(sql, field, entity);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        removeLastDelimiter(sql);
        return String.valueOf(sql);
    }

    private static void appendValue(StringBuilder sql, Field field, Entity entity) throws IllegalAccessException {
        String table = getTable(entity.getClass());
        sql
                .append(table)
                .append(".")
                .append(field.getName())
                .append(" = '")
                .append(field.get(entity))
                .append("' AND ");
    }

    private static void removeLastDelimiter(StringBuilder sql) {
        int startIndex = sql.lastIndexOf("AND");
        int endIndex = startIndex + "AND".length() + 1;
        sql.delete(startIndex, endIndex);
    }

}
*/
