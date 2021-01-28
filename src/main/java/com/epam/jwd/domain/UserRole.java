package com.epam.jwd.domain;

public enum UserRole implements Identified<Integer> {
    CLIENT(1),
    ADMIN(2),
    UNKNOWN(3);

    private final int id;

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
