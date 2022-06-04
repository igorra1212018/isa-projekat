package com.isa.fishingapp.dto;

public class UserDeletionRequestDTO {
	private Integer userId;
	private String description;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
