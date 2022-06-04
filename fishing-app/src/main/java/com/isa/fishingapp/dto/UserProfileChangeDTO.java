package com.isa.fishingapp.dto;

import com.isa.fishingapp.model.Country;

public class UserProfileChangeDTO {
	Integer id;
	String email;
	String oldPasswordGuess;
	String newPassword;
	String firstName;
	String lastName;
	String address;
	String city;
	Country country;
	String contactPhone;
	
	public UserProfileChangeDTO() {
	}
	
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getOldPasswordGuess() {
		return oldPasswordGuess;
	}
	public String getNewPassword() {
		return newPassword;
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
	public Country getCountry() {
		return country;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setCountry(Country country) {
		this.country = country;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
