package com.isa.fishingapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.fishingapp.model.Reservation;

public class ReserveReservableDTO {
	Integer reservableId;
	Integer userId;
	LocalDateTime fromDate;
	LocalDateTime toDate;
	List<Integer> amenities;
	Integer actionId;
	Integer price;
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
	public List<Integer> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<Integer> amenities) {
		this.amenities = amenities;
	}
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public static List<ReserveReservableDTO> convertReservationListToDTO(List<Reservation> reservationList) {
		List<ReserveReservableDTO> output = new ArrayList<>();
		for(Reservation r : reservationList) {
			output.add(new ReserveReservableDTO(r));
		}
		return output;
	}
	@Override
	public String toString() {
		return "ReserveReservableDTO [reservableId=" + reservableId + ", userId=" + userId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", amenities=" + amenities + "]";
	}
}
