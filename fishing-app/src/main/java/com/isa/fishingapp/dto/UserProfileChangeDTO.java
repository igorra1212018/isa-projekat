package com.isa.fishingapp.dto;

import com.isa.fishingapp.model.Location;

public class UserProfileChangeDTO {
	Integer id;
	String email;
	String oldPasswordGuess;
	String newPassword;
	String firstName;
	String lastName;
	Location residence;
	String contactPhone;
	
	public UserProfileChangeDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPasswordGuess() {
		return oldPasswordGuess;
	}

	public void setOldPasswordGuess(String oldPasswordGuess) {
		this.oldPasswordGuess = oldPasswordGuess;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Location getResidence() {
		return residence;
	}

	public void setResidence(Location residence) {
		this.residence = residence;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}
