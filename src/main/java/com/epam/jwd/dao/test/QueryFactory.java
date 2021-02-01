/*
package com.epam.jwd.dao.test;

import com.epam.jwd.context.annotation.Another;
import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Table;
import com.epam.jwd.domain.Entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class QueryFactory {
    private static QueryFactory instance;

    public static QueryFactory getInstance() {
        if (instance == null)
            instance = new QueryFactory();
        return instance;
    }

    public String buildSelectQuery(Entity entity) {
        return SelectQuery.build(entity);
    }

    public String buildSelectByCriteriaQuery(Entity entity) {
        return SelectQuery.build(entity) + SelectByCriteriaQuery.build(entity);
    }

    public String buildAddQuery(Entity entity) {
        return null;
    }

    public String buildUpdateQuery(Entity entity) {
        return null;
    }

    public String buildDeleteQuery(Entity entity) {
        return null;
    }

    protected static List<String> getColumns(Class<? extends Entity> cl) {
        List<String> columns = new ArrayList<>();
        for (Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            Column column = f.getAnnotation(Column.class);
            if (column != null) {
                columns.add(column.name());
            }
        }
        return columns;
    }

    protected static String getTable(Class<? extends Entity> cl) {
        Table annotation = cl.getAnnotation(Table.class);

        if(annotation != null){
            return annotation.name();
        }
        throw new RuntimeException("Table annotation not present");
    }

    */
/*protected static List<String> getJoins(Class<? extends Entity> cl) {
        StringBuilder sql = new StringBuilder();
        String parentTable = getTable(cl);
        List<Class<?>> joinsClasses = new ArrayList<>();
        List<String> joinNames = new ArrayList<>();
        for (Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            Another another = f.getAnnotation(Another.class);
            if (another != null) {
                joinsClasses.add(another.tClass());
            }
        }
        for (Class joinClass : joinsClasses) {
            String table = getTable(joinClass);
            sql.append("JOIN ");
            sql.append(table);
            sql.append(" ON ");
            sql.append(parentTable);
            sql.append(".");
            sql.append()
        }

    }*//*



}
*/
