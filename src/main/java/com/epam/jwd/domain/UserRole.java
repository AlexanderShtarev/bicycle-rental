package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

public enum UserRole implements BaseEntity {
    USER(1), ADMIN(2);

    private final int id;

    private UserRole(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name();
    }

    public static UserRole resolveUserRoleById(int id) throws UnknownEntityException {
        for (UserRole role : UserRole.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new UnknownEntityException("Entity With such id does not exist");
    }

}
