package com.isa.fishingapp.dto;

public class UserDeletionRequestDTO {
	private Integer userId;
	private String requestDescription;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
}
