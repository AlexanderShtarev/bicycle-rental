package com.epam.jwd.criteria;

import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;

import java.util.List;

public class UserCriteria extends Criteria<User> {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Double balance;
    private List<UserRole> roles;
    private UserStatus status;

    public UserCriteria(Criteria.Builder<? extends Criteria.Builder> builder) {
        super(builder);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Double getBalance() {
        return balance;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public static UserCriteria.Builder builder() {
        return new UserCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<UserCriteria.Builder> {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Double balance;
        private List<UserRole> roles;
        private UserStatus status;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder roles(List<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserCriteria build() {
            UserCriteria criteria = new UserCriteria(this);
            criteria.id = id;
            criteria.roles = roles;
            criteria.email = email;
            criteria.password = password;
            criteria.name = name;
            criteria.balance = balance;
            criteria.status = status;
            return criteria;
        }

    }

}
