package com.epam.jwd;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.context.annotation.Another;
import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Table;
import com.epam.jwd.criteria.ProductCriteria;
import com.epam.jwd.dao.builder.QueryBuilder;
import com.epam.jwd.dao.test.QueryFactory;
import com.epam.jwd.dao.test.SelectByCriteriaQuery;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Product;
import com.epam.jwd.domain.ProductType;
import com.epam.jwd.service.ProductService;
import com.epam.jwd.service.impl.ProductServiceImpl;

import java.lang.reflect.Field;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*ApplicationContext.APPLICATION_CONTEXT.init();
        ProductService productService = ProductServiceImpl.getInstance();
        productService.getAllProducts();
        ProductCriteria criteria = ProductCriteria.builder()
                .id(6L).build();
        Product product = productService.getSingleProductByCriteria(criteria);

        System.out.println(product);

        List<Product> productList =
                (List<Product>) ApplicationContext.APPLICATION_CONTEXT.getCache(Product.class);

        System.out.println(productList);*/

        //GETING PARENT COLUMNS AND PARENT TABLE, LINKS(LINK NAME, LINK TABLE NAME);

        // SELECT (COLUMNS) + JOINS ? +
        // + FROM + PARENT TABLE +
        // + JOIN ANOTHER.TABLE ON ANOTHER TABLE ID = PARENT TABLE.LINK_TO ANOTHER TABLE
        // + WHERE PARENT_TABLE + . + COLUMN + PARENT TABLE + COLUMNT etc...

        List<String> parentColumns = new ArrayList<>();

        Map<Class<?>, String> anotherEntitiesMap = new HashMap<>();

        for (Field f : ProductType.class.getDeclaredFields()) {
            f.setAccessible(true);
            Column column = f.getAnnotation(Column.class);
            Another another = f.getAnnotation(Another.class);

            if (column != null) {
                parentColumns.add(column.name());
            }
            if (another != null) {
                anotherEntitiesMap.put(another.tClass(), another.name());
            }
        }

        // У меня есть класс user;
        Map<String, List<String>> tableJoinsMap = new HashMap<>();
        List<String> joins = new ArrayList<>();
        List<String> joinTables = new ArrayList<>();
        for (Class cl : anotherEntitiesMap.keySet()) {
            Table annotation = (Table) cl.getAnnotation(Table.class);
            // теперь у меня есть название таблицы users

            String parentTableLink = anotherEntitiesMap.get(cl);
            // теперь есть название ссылки user_id

            for (Field f : cl.getDeclaredFields()) {
                f.setAccessible(true);
                Column column = f.getAnnotation(Column.class);
                if (column != null) {
                    joins.add(column.name());
                }
            }
            String sql = "JOIN " + annotation.name() + " ON " + annotation.name() + "." + "id = " + "parentTable." + parentTableLink;
            System.out.println(sql);
        }
        System.out.println(joins);
        System.out.println(joinTables);


        QueryFactory queryFactory = new SelectByCriteriaQuery();
        System.out.println(queryFactory.buildSelectByCriteriaQuery(ProductType.builder().name("namePR").build()));
    }



}
