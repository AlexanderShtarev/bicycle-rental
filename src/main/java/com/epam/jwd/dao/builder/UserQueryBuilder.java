package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.constant.UserFieldsConstant;
import com.epam.jwd.dao.constant.UserRoleFieldsConstant;
import com.epam.jwd.dao.constant.UserStatusFieldsConstant;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.UserRole;

public class UserQueryBuilder extends QueryBuilder{
    public static final UserQueryBuilder USER_QUERY_BUILDER = new UserQueryBuilder();

    @Override
    public String createQuery(Criteria<? extends Entity> criteria, String sqlGetAllUsers) {
        StringBuffer parameters = new StringBuffer();

        if (!(criteria instanceof UserCriteria)) {
            throw new RuntimeException("incomparable type of criteria");
        }

        UserCriteria userCriteria = (UserCriteria) criteria;

        if (null != userCriteria.getId()) {
            appendValue(parameters, UserFieldsConstant.USER_ID, userCriteria.getId());
        }

        if (userCriteria.getEmail() != null) {
            appendValue(parameters, UserFieldsConstant.USER_EMAIL, userCriteria.getEmail());
        }

        if (userCriteria.getPassword() != null) {
            appendValue(parameters, UserFieldsConstant.USER_PASSWORD, userCriteria.getPassword());
        }

        if (userCriteria.getName() != null) {
            appendValue(parameters, UserFieldsConstant.USER_NAME, userCriteria.getName());
        }

        if (userCriteria.getStatus() != null) {
            if (userCriteria.getStatus().getId() != null) {
                appendValue(parameters, UserStatusFieldsConstant.STATUS_ID, userCriteria.getStatus().getId());
            }
        }

        if (userCriteria.getBalance() != null) {
            appendValue(parameters, UserFieldsConstant.USER_BALANCE, userCriteria.getBalance());
        }

        return sqlGetAllUsers + " WHERE " + toStatement(parameters);
    }

}