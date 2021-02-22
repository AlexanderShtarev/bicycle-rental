package com.epam.jwd.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends Entity {

    private String name;

    private String surname;

    private String email;

    private String password;

    private BigDecimal balance;

    private List<UserRole> roles;

    private List<CreditCard> cards;

    private UserStatus status;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                ", roles=" + roles +
                ", cards=" + cards +
                ", status=" + status +
                '}';
    }
}
