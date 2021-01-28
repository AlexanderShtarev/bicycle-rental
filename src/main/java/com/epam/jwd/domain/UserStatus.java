package com.epam.jwd.domain;

public enum UserStatus implements Identified<Integer> {
    ACTIVE(1),
    NON_ACTIVE(2),
    BLOCKED(3);

    private final Integer id;

    UserStatus(Integer id) {
        this.id = id;
    }

    @Override
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
