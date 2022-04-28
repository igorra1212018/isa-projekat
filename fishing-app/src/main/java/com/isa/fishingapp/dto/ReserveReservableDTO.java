package com.isa.fishingapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.Reservation;

public class ReserveReservableDTO {
	Integer reservableId;
	Integer userId;
	LocalDateTime fromDate;
	LocalDateTime toDate;
	public ReserveReservableDTO() {
	}
	public ReserveReservableDTO(Reservation reservation) {
		this.reservableId = (reservation.getReservedEntity()).getId();
		this.userId = reservation.getUser().getId();
		this.fromDate = reservation.getDateRange().getFromDate();
		this.toDate = reservation.getDateRange().getToDate();
	}
	public Integer getReservableId() {
		return reservableId;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setReservableId(Integer lodgingId) {
		this.reservableId = lodgingId;
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
	public static List<ReserveReservableDTO> convertReservationListToDTO(List<Reservation> reservationList) {
		List<ReserveReservableDTO> output = new ArrayList<>();
		for(Reservation r : reservationList) {
			output.add(new ReserveReservableDTO(r));
		}
		return output;
	}
}
