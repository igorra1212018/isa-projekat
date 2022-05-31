package com.isa.fishingapp.dto;

import java.time.LocalDateTime;

public class ActionDTO {
	Integer reservableId;
	float discount;
	LocalDateTime fromDate;
	LocalDateTime toDate;
	public Integer getReservableId() {
		return reservableId;
	}
	public void setReservableId(Integer reservableId) {
		this.reservableId = reservableId;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
}
