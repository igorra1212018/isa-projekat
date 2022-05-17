package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="available_date_range")
public class AvailableDateRange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	DateRange range;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "reserable_id")
	Reservable reservable;

	public Integer getId() {
		return id;
	}

	public DateRange getRange() {
		return range;
	}

	@JsonIgnore
	public Reservable getReservable() {
		return reservable;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRange(DateRange range) {
		this.range = range;
	}

	public void setReservable(Reservable reservable) {
		this.reservable = reservable;
	}
}
