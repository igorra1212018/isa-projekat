package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("RESERVATION_BOAT")
public class ReservationBoat extends Reservation {
	public ReservationBoat() {
		super();
	}
	
	public ReservationBoat(User user, DateRange dateRange, Reservable reservedEntity) {
		super(user, dateRange, reservedEntity);
	}
}
