package com.epam.jwd.service;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    boolean addUser(User user);

    List<User> findByCriteria(Criteria<? extends User> criteria);

}
