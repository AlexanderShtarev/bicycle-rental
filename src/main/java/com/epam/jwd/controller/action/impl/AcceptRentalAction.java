package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.Rental;
import com.epam.jwd.domain.RentalStatus;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.RentalService;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.RentalServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptRentalAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRentalAction.class);
    private final RentalService rentalService;
    private final UserService userService;

    public AcceptRentalAction() {
        this.rentalService = RentalServiceImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("accept rental action received");

        int rentalId = Integer.parseInt(request.getParameter(RequestConstant.RENTAL_ID));
        Rental rental = rentalService.getById(rentalId);

        User user = userService.getUserById(rental.getUser().getId());
        user.setStatus(UserStatus.RENTING);

        userService.updateUser(user);
        rental.setStatus(RentalStatus.ACCEPTED);

        userService.sendMailMessage("Your rental accepted", "", user.getEmail());
        rentalService.updateRental(rental);
    }
}
