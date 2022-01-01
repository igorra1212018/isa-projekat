package com.isa.fishingapp.model;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
	private final String address;
	private final String city;
	private final String country;
	
	public Location() {
		this.address = "";
		this.city = "";
		this.country = ""; 
	}
	
	public Location(String address, String city, String country) {
		this.address = address;
		this.city = city;
		this.country = country;
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
