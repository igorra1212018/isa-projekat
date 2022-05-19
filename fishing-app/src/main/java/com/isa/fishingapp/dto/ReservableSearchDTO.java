package com.isa.fishingapp.dto;

import java.time.LocalDateTime;

import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Location;

public class ReservableSearchDTO {
	String name;
	Location location;
	DateRange dateRange;
	String sortType = "name";
	String sortDir = "ascending";
	
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
	public DateRange getDateRange() {
		return dateRange;
	}
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	public String getSortType() {
		return sortType;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	@Override
	public String toString() {
		return "ReservableSearchDTO [name=" + name + ", location=" + location + ", dateRange=" + dateRange + "]";
	}
	
}
