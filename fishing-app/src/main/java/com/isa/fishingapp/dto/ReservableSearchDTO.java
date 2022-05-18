package com.isa.fishingapp.dto;

import java.time.LocalDateTime;

import com.isa.fishingapp.model.Location;

public class ReservableSearchDTO {
	String name;
	Location location;
	LocalDateTime dateFrom;
	LocalDateTime dateTo;
	
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
	public LocalDateTime getDateFrom() {
		return dateFrom;
	}
	public LocalDateTime getDateTo() {
		return dateTo;
	}
	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}
	@Override
	public String toString() {
		return "ReservableSearchDTO [name=" + name + ", location=" + location + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + "]";
	}
}
