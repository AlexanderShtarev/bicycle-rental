package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.NotEmpty;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class VerificationToken extends Entity implements Identified<Long> {

    @NotEmpty
    @Column(name = "token")
    private String token;

    @NotEmpty
    @Column(name = "created_date")
    private Date createdDate;

    @NotEmpty
    private User user;

    public VerificationToken(VerificationToken.Builder builder) {
    }

    public VerificationToken(User user) {
        this.user = user;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationToken that = (VerificationToken) o;
        return id.equals(that.id) &&
                token.equals(that.token) &&
                createdDate.equals(that.createdDate) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, createdDate, user);
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                '}';
    }

    public static VerificationToken.Builder builder() {
        return new VerificationToken.Builder();
    }

    public static class Builder {
        private Long id;
        private String token;
        private Date createdDate;
        private User user;

        private Builder() {
        }

        public VerificationToken.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public VerificationToken.Builder token(String token) {
            this.token = token;
            return this;
        }

        public VerificationToken.Builder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public VerificationToken.Builder user(User user) {
            this.user = user;
            return this;
        }

        public VerificationToken build() {
            VerificationToken verificationToken = new VerificationToken(this);
            verificationToken.id = id;
            verificationToken.token = token;
            verificationToken.createdDate = createdDate;
            verificationToken.user = user;
            return verificationToken;
        }

    }

}
