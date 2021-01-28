package com.epam.jwd.domain;

public enum RentalStatus implements Identified<Integer> {
    PENDING_VERIFICATION(1),
    AWAITING_APPROVAL(2),
    NOT_ACCEPTED(3),
    ACCEPTED(4),
    CANCELED(5),
    IN_PROGRESS(6),
    COMPLETED(7);

    private final Integer id;

    RentalStatus(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    public static RentalStatus resolveStatusById(Integer id) {
        for (RentalStatus status : RentalStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

}
