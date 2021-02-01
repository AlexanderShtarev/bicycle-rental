package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.impl.Cart;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class RentProductCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String rentalTime = request.getParameter(RequestConstant.RENTAL_TIME);

        Cart cart = (Cart) session.getAttribute(RequestConstant.CART);
        User user = (User) session.getAttribute(RequestConstant.USER);

        Double total = cart.getTotalAmount();

        if (user.getStatus().equals(UserStatus.BLOCKED) || user.getStatus().equals(UserStatus.NON_ACTIVE)) {
            request.setAttribute(RequestConstant.ERROR, "You can not do this, please contact administrators for more info");
            forward(PageConstant.ERROR_PAGE);
        }

        Rental rental = Rental.builder()
                .user(user)
                .status(RentalStatus.PENDING_VERIFICATION)
                .total(total)
                .rentalDate(new Date())
                .build();

        RentalService.create(rental);

        forward(RequestConstant.PAGE);

    }

}
