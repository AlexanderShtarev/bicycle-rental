package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.NotEmpty;
import com.epam.jwd.context.annotation.Table;

@Table(name = "user_status")
public enum UserStatus {
    ACTIVE(1),
    NON_ACTIVE(2),
    BLOCKED(3);

    @NotEmpty
    private final Integer id;

    UserStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    public static UserStatus resolveStatusById(Integer id) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

}
