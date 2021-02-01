package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.NotEmpty;

public enum UserRole implements Identified<Integer>  {
    CLIENT(1),
    ADMIN(2),
    UNKNOWN(3);

    @NotEmpty
    private final Integer id;

    UserRole(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName(){
        return name();
    }

    public static UserRole resolveRoleById(Integer id) {
        for (UserRole role : UserRole.values()) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }

}
