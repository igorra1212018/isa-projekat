package com.isa.fishingapp.dto;

import java.time.LocalDateTime;

import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Location;

public class ReservableSearchDTO {
	String name;
	Location location;
	DateRange dateRange;
	Integer fromPrice = 0;
	Integer toPrice = 10000;
	Integer fromCapacity = 0;
	Integer toCapacity = 100;
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
	public Integer getFromPrice() {
		return fromPrice;
	}
	public void setFromPrice(Integer fromPrice) {
		this.fromPrice = fromPrice;
	}
	public Integer getToPrice() {
		return toPrice;
	}
	public void setToPrice(Integer toPrice) {
		this.toPrice = toPrice;
	}
	public Integer getFromCapacity() {
		return fromCapacity;
	}
	public void setFromCapacity(Integer fromCapacity) {
		this.fromCapacity = fromCapacity;
	}
	public Integer getToCapacity() {
		return toCapacity;
	}
	public void setToCapacity(Integer toCapacity) {
		this.toCapacity = toCapacity;
	}
	@Override
	public String toString() {
		return "ReservableSearchDTO [name=" + name + ", location=" + location + ", dateRange=" + dateRange
				+ ", fromPrice=" + fromPrice + ", toPrice=" + toPrice + ", fromCapacity=" + fromCapacity
				+ ", toCapacity=" + toCapacity + ", sortType=" + sortType + ", sortDir=" + sortDir + "]";
	}
}
