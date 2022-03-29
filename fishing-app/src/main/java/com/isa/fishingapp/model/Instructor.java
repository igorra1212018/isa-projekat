package com.isa.fishingapp.model;

import java.util.List;

public class Instructor extends User{
	private List<FishingLesson> services;
	private DateRange availability;
	
	public List<FishingLesson> getServices() {
		return services;
	}
	public void setServices(List<FishingLesson> services) {
		this.services = services;
	}
	public DateRange getAvailability() {
		return availability;
	}
	public void setAvailability(DateRange availability) {
		this.availability = availability;
	}
}
