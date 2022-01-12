package com.isa.fishingapp.model;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
	private final String address;
	private final String city;
	private final String country;
	private final double latitude;
	private final double longtitude;
	
	public Location() {
		this.address = "";
		this.city = "";
		this.country = ""; 
		this.latitude = 0;
		this.longtitude = 0;
	}
	
	public Location(String address, String city, String country, double latitude, double longtitude) {
		this.address = address;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
}
