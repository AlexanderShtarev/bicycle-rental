package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

public enum UserStatus implements BaseEntity {
    ACTIVE(1), BLOCKED(2), NON_ACTIVE(3), RENTING(4), WAITING_FOR_RESPONSE(5);

    private final int id;

    private UserStatus(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name();
    }

    public static UserStatus resolveUserStatusById(int id) throws UnknownEntityException {
        for (UserStatus status : UserStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownEntityException("Entity With such id does not exist");
    }

}
