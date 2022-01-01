package com.isa.fishingapp.dto;

import com.isa.fishingapp.model.Location;

public class UserDTO {
	String email;
	String password;
	String firstName;
	String lastName;
	String address;
	String city;
	String country;
	String contactPhone;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
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
	public String getContactPhone() {
		return contactPhone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
