package com.epam.jwd.filter;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class CommandFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFilter.class);
    public static final List<String> adminCommands = new ArrayList<>() {{
        add("add_category");
        add("add_manufacturer");
        add("add_product");
        add("to_update_product_page");
        add("update_product");
        add("to_view_products");
        add("to_view_users");
        add("to_view_categories");
        add("to_view_manufacturers");
        add("to_admin");
        add("to_view_rentals");
        add("view_users");
        add("delete_category");
        add("delete_manufacturer");
        add("delete_product");
        add("change_user_status");
        add("accept_rental");
        add("decline_rental");
    }};

    public static final List<String> userCommands = new ArrayList<>() {{
        add("to_profile_page");
        add("update_user");
        add("add_card");
        add("delete_card");
        add("top_up_balance");
        add("to_cart_page");
        add("rent");
        add("add_product_to_cart");
        add("remove_product_from_cart");
        add("view_cart");
        add("view_user_rentals");
    }};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute(RequestConstant.USER);

        String commandName = req.getParameter("command");
        if (commandName == null) {
            commandName = req.getParameter("action");
        }

        if (user != null && user.getStatus().equals(UserStatus.BLOCKED)) {

            LOGGER.trace("Redirecting user to forbidden page");
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        boolean access = resolveAccess(user, commandName);

        if (!access) {
            LOGGER.trace("Redirecting user to unauthorized page");
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(req, resp);
    }

    private boolean resolveAccess(User user, String commandName) {
        LOGGER.trace("Resolving user access");
        boolean result;
        if (user == null || user.getStatus().equals(UserStatus.NON_ACTIVE)) {
            result = !adminCommands.contains(commandName) && !userCommands.contains(commandName);
        } else if (!user.getRoles().contains(UserRole.ADMIN) && user.getRoles().contains(UserRole.USER)) {
            result = !adminCommands.contains(commandName);
        } else {
            result = true;
        }

        return result;
    }
}
