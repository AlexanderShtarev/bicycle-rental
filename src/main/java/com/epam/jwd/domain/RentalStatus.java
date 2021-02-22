package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

public enum RentalStatus implements BaseEntity{
    WAITING(1), ACCEPTED(2), DECLINED(3), IN_PROGRESS(4), FINISHED(5);

    private final int id;

    private RentalStatus(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name();
    }

    public static RentalStatus resolveRentalStatusById(int id) throws UnknownEntityException {
        for (RentalStatus status : RentalStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownEntityException("Entity With such id does not exist");
    }


}
