package com.epam.jwd.service;

import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;

public interface MailService {

    void sendVerificationToken(User user);

    VerificationToken getVerificationToken(String token);

}
