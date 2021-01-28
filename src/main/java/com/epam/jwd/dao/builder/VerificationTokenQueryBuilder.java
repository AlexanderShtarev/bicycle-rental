package com.epam.jwd.dao.builder;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.VerificationTokenCriteria;
import com.epam.jwd.dao.constant.VerificationTokenFieldsConstant;
import com.epam.jwd.domain.Entity;

public class VerificationTokenQueryBuilder extends QueryBuilder {
    public static final VerificationTokenQueryBuilder VERIFICATION_TOKEN_QUERY_BUILDER = new VerificationTokenQueryBuilder();

    @Override
    public String createQuery(Criteria<? extends Entity> criteria, String sqlGetAllTokens) {
        StringBuffer parameters = new StringBuffer();

        if (!(criteria instanceof VerificationTokenCriteria)) {
            throw new RuntimeException("incomparable type of criteria");
        }

        VerificationTokenCriteria tokenCriteria = (VerificationTokenCriteria) criteria;

        if (null != tokenCriteria.getId()) {
            appendValue(parameters, VerificationTokenFieldsConstant.VERIFICATION_TOKEN_ID, tokenCriteria.getId());
        }

        if (tokenCriteria.getToken() != null) {
            appendValue(parameters, VerificationTokenFieldsConstant.VERIFICATION_TOKEN_TOKEN, tokenCriteria.getToken());
        }

        if (tokenCriteria.getCreatedDate() != null) {
            appendValue(parameters, VerificationTokenFieldsConstant.VERIFICATION_TOKEN_CREATED_DATE, tokenCriteria.getCreatedDate());
        }

        if (tokenCriteria.getUser() != null) {
            if (tokenCriteria.getUser().getId() != null) {
                appendValue(parameters, VerificationTokenFieldsConstant.VERIFICATION_TOKEN_USER_ID, tokenCriteria.getUser().getId());
            }
        }

        return sqlGetAllTokens + " WHERE " + toStatement(parameters);
    }

}
