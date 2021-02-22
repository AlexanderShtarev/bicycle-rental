package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.RentalService;
import com.epam.jwd.service.impl.RentalServiceImpl;
import com.epam.jwd.util.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewUserRentalsAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewUserRentalsAction.class);
    private final RentalService rentalService;

    public ViewUserRentalsAction() {
        this.rentalService = RentalServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("View rentals action received");

        User user = (User) request.getSession().getAttribute(RequestConstant.USER);
        List<Rental> rentals = rentalService.getRentalsByUserId(user.getId());

        if (!rentals.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseRentalsToJSON(rentals));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
