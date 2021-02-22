package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.CartService;
import com.epam.jwd.service.RentalService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RentCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentCommand.class);
    private final RentalService rentalService;
    private final UserService userService;
    private final CartService cartService;

    public RentCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.rentalService = serviceFactory.getRentalService();
        this.userService = serviceFactory.getUserService();
        this.cartService = serviceFactory.getCartService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        String rentalDate = request.getParameter(RequestConstant.RENTAL_DATE);
        String returnDate = request.getParameter(RequestConstant.RETURN_DATE);
        User user = (User) request.getSession().getAttribute(RequestConstant.USER);
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(RequestConstant.CART_BEAN);

        if (cartService.getTotalPrice(cart).compareTo(user.getBalance()) > 0) {
            LOGGER.info("user balance is lesser than cart cost");
            request.setAttribute(RequestConstant.ERROR, "You don't have enough money on your balance");
            return new ViewResolver(PageConstant.CART_PAGE);
        }

        if (user.getStatus().equals(UserStatus.RENTING) || user.getStatus().equals(UserStatus.WAITING_FOR_RESPONSE)) {
            LOGGER.info("user already renting");
            request.setAttribute(RequestConstant.ERROR, "You are already renting");
            return new ViewResolver(PageConstant.CART_PAGE);
        }

        try {
            Date rentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(rentalDate);
            Date retDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(returnDate);

            List<String> errors = new ArrayList<>();

            Validator.checkDateMoreThanNow(rentDate).ifPresent(errors::add);
            Validator.checkDateMoreThanNow(retDate).ifPresent(errors::add);

            if (rentDate.getTime() > retDate.getTime()) {
                LOGGER.info("user messed up with date");
                request.setAttribute(RequestConstant.ERROR, "Please choose correct time");
                return new ViewResolver(PageConstant.CART_PAGE);
            }

            if (errors.isEmpty()) {
                List<RentalItem> rentalItems = new ArrayList<>();

                for (Map.Entry<Product, Integer> entry : cart.getProductMap().entrySet()) {
                    RentalItem rentalItem = new RentalItem(entry.getKey(), entry.getKey().getPrice(), entry.getValue());
                    rentalItems.add(rentalItem);
                }

                Rental rental = Rental.builder()
                        .user(user)
                        .rentalDate(rentDate)
                        .returnDate(retDate)
                        .rentalItems(rentalItems)
                        .status(RentalStatus.WAITING)
                        .build();

                rentalService.createRental(rental);
                cart.getProductMap().clear();
                userService.updateUserCart(cart, user.getId());
                user.setStatus(UserStatus.WAITING_FOR_RESPONSE);
                userService.updateUser(user);

                userService.sendMailMessage("Rental", "Please wait for our admins to confirm your rental",
                        user.getEmail());

                request.getSession().setAttribute(RequestConstant.CART_BEAN, cart);
                request.getSession().setAttribute(RequestConstant.USER, user);
                LOGGER.info("All good");
            } else {
                request.setAttribute(RequestConstant.ERRORS, errors);
            }

        } catch (ParseException e) {
            LOGGER.warn("Can't parse date, ", e);
        }

        return new ViewResolver(PageConstant.HOME_PAGE, ResolveAction.REDIRECT);
    }

}
