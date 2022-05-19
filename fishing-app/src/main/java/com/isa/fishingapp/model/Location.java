package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Location {
	private final String address;
	private final String city;
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	private final double latitude;
	private final double longtitude;
	
	public Location() {
		this.address = "";
		this.city = "";
		this.country = null;
		this.latitude = 0;
		this.longtitude = 0;
	}
	
	public Location(String address, String city, Country country, double latitude, double longtitude) {
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

	public Country getCountry() {
		return country;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", city=" + city + ", country=" + country + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + "]";
	}
}
