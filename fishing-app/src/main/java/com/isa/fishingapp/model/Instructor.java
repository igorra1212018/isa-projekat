package com.isa.fishingapp.model;

public class Instructor extends User{
	private DateRange availability;
	
	public DateRange getAvailability() {
		return availability;
	}
	public void setAvailability(DateRange availability) {
		this.availability = availability;
	}
}
