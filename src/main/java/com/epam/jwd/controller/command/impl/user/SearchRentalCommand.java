package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.RentalCriteria;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class SearchRentalCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String statusId = request.getParameter(RequestConstant.RENTAL_STATUS_ID);
        String date = request.getParameter(RequestConstant.RENTAL_DATE);
        String userId = request.getParameter(RequestConstant.USER_ID);

        RentalCriteria criteria = RentalCriteria.builder()
                .status(RentalStatus.resolveStatusById(Integer.valueOf(statusId)))
                .user(User.builder().id(Long.valueOf(userId)).build())
                .build();

        Rental rental = RentalService.findByCriteria(criteria);
        if (rental == null) {
            rental = RentalService.findByRentalDate(date);
        }
        if (rental == null) {
            request.setAttribute(RequestConstant.ERROR, "Rentals not found");
        }

        forward(RequestConstant.PAGE);
    }

}
