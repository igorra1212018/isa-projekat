package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RESERVATION_LODGING")
public class ReservationLodging extends Reservation {
	
	public ReservationLodging() {
		super();
	}
	
	public ReservationLodging(User user, DateRange dateRange, Reservable reservedEntity) {
		super(user, dateRange, reservedEntity);
	}
}
