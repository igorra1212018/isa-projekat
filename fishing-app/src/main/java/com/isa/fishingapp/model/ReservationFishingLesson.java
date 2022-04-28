package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_fishing_lesson")
@DiscriminatorValue("RESERVATION_FISHING_LESSON")
public class ReservationFishingLesson extends Reservation {
	public ReservationFishingLesson() {
		super();
	}
	
	public ReservationFishingLesson(User user, DateRange dateRange, Reservable reservedEntity) {
		super(user, dateRange, reservedEntity);
	}
}
