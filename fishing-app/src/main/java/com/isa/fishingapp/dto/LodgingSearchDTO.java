package com.isa.fishingapp.dto;

import com.isa.fishingapp.model.Location;

public class LodgingSearchDTO {
	String name;
	Location location;
	
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return location;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
