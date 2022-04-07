package com.isa.fishingapp.dto;

public class OwnerDTO extends UserDTO{
	private String type;
	private String applicationDetails;
	private String role;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApplicationDetails() {
		return applicationDetails;
	}
	public void setApplicationDetails(String applicationDetails) {
		this.applicationDetails = applicationDetails;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
