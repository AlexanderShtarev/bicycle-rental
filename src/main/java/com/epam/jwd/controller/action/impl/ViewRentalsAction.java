package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Rental;
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

public class ViewRentalsAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewRentalsAction.class);
    private final RentalService rentalService;

    public ViewRentalsAction() {
        this.rentalService = RentalServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("View rentals action received");

        List<Rental> rentals = rentalService.getALl();

        if (!rentals.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseRentalsToJSON(rentals));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
