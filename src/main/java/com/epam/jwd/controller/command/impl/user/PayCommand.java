package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class PayCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(RequestConstant.USER);

        String card = request.getParameter(RequestConstant.CARD);
        String rentalId = request.getParameter(RequestConstant.RENTAL_ID);

        Rental rental = RentalService.findRental(rentalId);

        rental.setStatus(RentalStatus.IN_PROGRESS);


    }

}
