package com.epam.jwd.domain;

import java.util.List;
import java.util.Objects;

public class User extends Entity {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Double balance;
    private List<UserRole> roles;
    private UserStatus status;

    public User() {
    }

    public User(String name, String email, String password, Double balance, List<UserRole> roles, UserStatus status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
        this.status = status;
    }

    public User(User.Builder builder) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                balance.equals(user.balance) &&
                roles.equals(user.roles) &&
                status.equals(user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, balance, roles, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Double balance;
        private List<UserRole> roles;
        private UserStatus status;

        private Builder() {
        }

        public User.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public User.Builder roles(List<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public User.Builder email(String email) {
            this.email = email;
            return this;
        }

        public User.Builder password(String password) {
            this.password = password;
            return this;
        }

        public User.Builder name(String name) {
            this.name = name;
            return this;
        }

        public User.Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public User.Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public User build() {
            User user = new User(this);
            user.id = id;
            user.roles = roles;
            user.email = email;
            user.password = password;
            user.name = name;
            user.balance = balance;
            user.status = status;
            return user;
        }

    }

}
