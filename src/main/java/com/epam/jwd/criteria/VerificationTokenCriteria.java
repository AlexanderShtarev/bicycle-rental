package com.epam.jwd.criteria;

import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;

import java.util.Date;

public class VerificationTokenCriteria extends Criteria<VerificationToken> {

    private Long id;
    private String token;
    private Date createdDate;
    private User user;

    public VerificationTokenCriteria(Criteria.Builder<? extends Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public User getUser() {
        return user;
    }

    public static VerificationTokenCriteria.Builder builder() {
        return new VerificationTokenCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<VerificationTokenCriteria.Builder> {
        private Long id;
        private String token;
        private Date createdDate;
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        @Override
        public VerificationTokenCriteria build() {
            VerificationTokenCriteria criteria = new VerificationTokenCriteria(this);
            criteria.id = id;
            criteria.token = token;
            criteria.createdDate = createdDate;
            criteria.user = user;
            return criteria;
        }

    }

}