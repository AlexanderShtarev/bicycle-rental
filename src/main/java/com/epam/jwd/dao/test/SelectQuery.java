/*
package com.epam.jwd.dao.test;

import com.epam.jwd.context.annotation.Another;
import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Table;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.ProductType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectQuery extends QueryFactory {

    public static String build(Entity entity) {
        Map<Class<?>, String> anotherEntitiesMap = new HashMap<>();
        List<String> parentColumns = getColumns(entity.getClass());
        List<String> joinColumns = getJoinColumns(entity.getClass());
        String parentTable = getTable(entity.getClass());
        */
/*String joinTable = getJoinTable(entity.getClass());
        String joinTableId = getJoinTableId(entity.getClass());
        String parentTableLinkToJoin = getParentTableLinkToJoin(entity.getClass());*//*


        parentColumns.addAll(getJoinColumns(entity));

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT (");

        for (String column : parentColumns) {
            sql.append(column).append(", ");
        }
        removeLastDelimiter(sql);

        sql.append(") FROM ").append(parentTable);

        */
/*sql.append(" ").append(getJoins(entity.getClass()));*//*


        return String.valueOf(sql);
    }

    private static void removeLastDelimiter(StringBuilder sql) {
        int startIndex = sql.lastIndexOf(",");
        int endIndex = startIndex + ",".length() + 1;
        sql.delete(startIndex, endIndex);
    }

    private static List<String> getJoinColumns(Class<?> cl) {

    }
}
*/
