package com.isa.fishingapp.dto;

public class ReviewDTO {
    private Integer userId;
    private Integer reservableId;
    private int rating;
	private String description;
	
	public Integer getUserId() {
		return userId;
	}
	public Integer getReservableId() {
		return reservableId;
	}
	public int getRating() {
		return rating;
	}
	public String getDescription() {
		return description;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setReservableId(Integer reservableId) {
		this.reservableId = reservableId;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
