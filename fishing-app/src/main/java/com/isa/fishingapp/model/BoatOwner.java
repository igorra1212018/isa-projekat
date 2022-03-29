package com.isa.fishingapp.model;

import java.util.List;

public class BoatOwner extends User{
	private List<Boat> boats;
	private DateRange availability;
	
	public List<Boat> getBoats() {
		return boats;
	}
	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}
	public DateRange getAvailability() {
		return availability;
	}
	public void setAvailability(DateRange availability) {
		this.availability = availability;
	}
	
}
