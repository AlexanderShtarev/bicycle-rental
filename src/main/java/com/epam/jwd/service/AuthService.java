package com.epam.jwd.service;

import com.epam.jwd.domain.User;

public interface AuthService {

    boolean login(String password, String hashPassword);

    boolean register(User user);

    boolean checkIfUserExist(String email);


}
