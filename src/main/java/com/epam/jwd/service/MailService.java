package com.epam.jwd.service;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;

public interface MailService {

    void send();

    VerificationToken findTokenByCriteria(Criteria<? extends VerificationToken> criteria);

    boolean validateToken(VerificationToken token, User user);

    VerificationToken createVerificationToken(User user);

}
