package com.isa.fishingapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.fishingapp.model.ReservationLodging;

public class ReserveLodgingDTO {
	Integer lodgingId;
	Integer userId;
	LocalDateTime fromDate;
	LocalDateTime toDate;
	public ReserveLodgingDTO() {
	}
	public ReserveLodgingDTO(ReservationLodging reservation) {
		this.lodgingId = reservation.getLodging().getId();
		this.userId = reservation.getUser().getId();
		this.fromDate = reservation.getDateRange().getFromDate();
		this.toDate = reservation.getDateRange().getToDate();
	}
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
	public static List<ReserveLodgingDTO> convertReservationLodgingListToDTO(List<ReservationLodging> reservationList) {
		List<ReserveLodgingDTO> output = new ArrayList<>();
		for(ReservationLodging r : reservationList) {
			output.add(new ReserveLodgingDTO(r));
		}
		return output;
	}
}
