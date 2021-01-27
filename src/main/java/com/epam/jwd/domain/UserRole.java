package com.epam.jwd.domain;

public enum UserRole {
    CLIENT(1),
    ADMIN(2),
    UNKNOWN(3);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name();
    }

    public static UserRole resolveRoleById(long id) {
        for (UserRole role : UserRole.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }

}
