package com.epam.jwd.controller.action;

import com.epam.jwd.controller.action.impl.*;
import com.epam.jwd.controller.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);
    private static final Map<String, Action> COMMAND_MAP = initCommands();
    private static ActionFactory instance;

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }

    private ActionFactory() {
    }

    private static Map<String, Action> initCommands() {
        LOGGER.trace("Initializing action map");
        return Map.ofEntries(
                Map.entry("view_products", new ViewProductsAction()),
                Map.entry("view_categories", new ViewCategoriesAction()),
                Map.entry("view_manufacturers", new ViewManufacturersAction()),
                Map.entry("view_users", new ViewUsersAction()),
                Map.entry("delete_category", new DeleteCategoryAction()),
                Map.entry("delete_manufacturer", new DeleteManufacturerAction()),
                Map.entry("delete_product", new DeleteProductAction()),
                Map.entry("view_home", new ViewHomeAction()),
                Map.entry("add_product_to_cart", new AddToCartAction()),
                Map.entry("remove_product_from_cart", new RemoveFromCartAction()),
                Map.entry("remove_all_products_from_cart", new RemoveAllFromCartAction()),
                Map.entry("view_cart", new ViewCartAction()),
                Map.entry("view_user_rentals", new ViewUserRentalsAction()),
                Map.entry("change_user_status", new ChangeUserStatusAction()),
                Map.entry("view_rentals", new ViewRentalsAction()),
                Map.entry("accept_rental", new AcceptRentalAction()),
                Map.entry("decline_rental", new DeclineRentalAction())
        );
    }

    public Action getAction(String commandName) {
        return COMMAND_MAP.get(commandName);
    }

}
