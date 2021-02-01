package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getUsersByCriteria(Criteria<? extends User> criteria);

    Optional<User> getSingleUserByCriteria(Criteria<? extends User> criteria);

    Object deleteById(Connection con, Long userId);

    void update(Connection con, User user);

    Object persist(Connection con, User user);

}
