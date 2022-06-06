package com.isa.fishingapp.dto;

public class ReservationDTO {

	float price;
	String userEmail;
	String from;
	String to;
	String reservableId;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getReservableId() {
		return reservableId;
	}
	public void setReservableId(String reservableId) {
		this.reservableId = reservableId;
	}
	
	
}
