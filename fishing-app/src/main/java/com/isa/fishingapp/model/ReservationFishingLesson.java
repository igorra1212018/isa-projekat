package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("RESERVATION_FISHING_LESSON")
public class ReservationFishingLesson extends Reservation {
	public ReservationFishingLesson() {
		super();
	}
	
	public ReservationFishingLesson(User user, DateRange dateRange, Reservable reservedEntity) {
		super(user, dateRange, reservedEntity);
	}

	@Override
	public String toString() {
		return "ReservationFishingLesson [getId()=" + getId() + ", getType()=" + getType() + ", getPrice()="
				+ getPrice() + ", getUser()=" + getUser() + ", getDateRange()=" + getDateRange()
				+ ", getReservedEntity()=" + getReservedEntity() + ", getAction()=" + getAction() + ", getAmenities()="
				+ getAmenities() + ", getComplaint()=" + getComplaint() + ", isCancelled()=" + isCancelled()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
