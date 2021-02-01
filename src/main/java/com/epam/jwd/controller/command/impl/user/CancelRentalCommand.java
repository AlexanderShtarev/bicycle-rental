package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.Payment;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;

import javax.servlet.ServletException;
import java.io.IOException;

public class CancelRentalCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String rentalId = request.getParameter(RequestConstant.RENTAL_ID);

        Rental rental = RentalService.find(rentalId);

        rental.setStatus(RentalStatus.CANCELED);

        forward(RequestConstant.PAGE);

        Payment payment = new Payment();

        if (PaymentService.processPayment()) {
            forward(RequestConstant.PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Payment not proceed");
            forward(PageConstant.ERROR_PAGE);
        }

    }

}
