package com.epam.jwd.service;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    Collection<User> getAllUsers();

    Collection<User> getAllUsersByCriteria(Criteria<? extends User> userCriteria);

    User getSingleUserByCriteria(Criteria<? extends User> userCriteria);

    void registerUser(User user);

    void updateUser(User user);

}
