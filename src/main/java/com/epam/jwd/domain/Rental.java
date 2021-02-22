package com.epam.jwd.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rental extends Entity {

    User user;

    Date rentalDate;

    Date returnDate;

    RentalStatus status;

    List<RentalItem> rentalItems;

}
