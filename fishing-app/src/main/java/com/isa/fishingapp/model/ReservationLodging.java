package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.isa.fishingapp.dto.ReserveLodgingDTO;

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
