package com.isa.fishingapp.dto;

public class ComplaintDTO {
	private Integer userId;
    private Integer reservationId;
	private String description;
	
	public Integer getUserId() {
		return userId;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public String getDescription() {
		return description;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
