package com.isa.fishingapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionDTO {
	private int reservableId;
	private float discount;
	
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private LocalDateTime validUntilDate;
	
	private String from;
	private String to;
	private String validUntil;
	
	public ActionDTO() {}
	
	public void transformToDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		fromDate = LocalDate.parse(from, formatter).atStartOfDay();
		toDate = LocalDate.parse(to, formatter).atStartOfDay();
		validUntilDate = LocalDate.parse(validUntil, formatter).atStartOfDay();
	}

	public int getReservableId() {
		return reservableId;
	}

	public void setReservableId(int reservableId) {
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

	public LocalDateTime getValidUntilDate() {
		return validUntilDate;
	}

	public void setValidUntilDate(LocalDateTime validUntilDate) {
		this.validUntilDate = validUntilDate;
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

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}
	


}
