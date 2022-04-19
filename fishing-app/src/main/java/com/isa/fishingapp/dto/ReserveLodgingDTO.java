package com.isa.fishingapp.dto;

import java.time.LocalDateTime;

public class ReserveLodgingDTO {
	Integer lodgingId;
	Integer userId;
	LocalDateTime fromDate;
	LocalDateTime toDate;
	public Integer getLodgingId() {
		return lodgingId;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setLodgingId(Integer lodgingId) {
		this.lodgingId = lodgingId;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
