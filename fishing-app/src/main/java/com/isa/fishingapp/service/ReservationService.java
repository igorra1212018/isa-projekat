package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.repository.ActionRepository;
import com.isa.fishingapp.repository.ReservationRepository;
import com.isa.fishingapp.model.Action;
import com.isa.fishingapp.model.AvailableDateRange;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservation;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ActionRepository actionRepository;
	
	public ReservationService() {
	}
	
	public Reservation findById(int id)
	{
		return reservationRepository.findById(id).orElse(null);
	}
	
	public List<Reservation> findByEntityId(int entityId)
	{
		return reservationRepository.findByReservedEntity_Id(entityId);
	}
	
	public List<Reservation> findByEntityIdAndCancelled(int entityId, boolean cancelled) {
		return reservationRepository.findByReservedEntity_IdAndCancelled(entityId, cancelled);
	}
	
	public List<Reservation> findAllReservationsInInterval(Integer entityId, DateRange dateRange)
	{
		return reservationRepository.findReservationEntitiesInInterval(entityId, dateRange.getFromDate(), dateRange.getToDate());
	}
	
	public List<Reservation> findByUserId(Integer userId)
	{
		return reservationRepository.findByUserId(userId);
	}
	
	public List<Action> findByReservable_IdAndAvailable(Integer reservableId)
	{
		return actionRepository.findByReservable_IdAndAvailable(reservableId);
	}
	
	public Reservation cancelReservation(Integer reservationId)
	{
		Reservation reservationToCancel = reservationRepository.findById(reservationId).orElse(null);
		if(reservationToCancel != null)
		{
			reservationToCancel.setCancelled(true);
			reservationRepository.save(reservationToCancel);
		}
		return reservationToCancel;
	}

	public void save(Reservation reservation) {
		for(AvailableDateRange r : reservation.getReservedEntity().getAvailableDateRanges())
			if(r.getRange().hasWithinDateRange(reservation.getDateRange()))
				reservationRepository.save(reservation);
	}
}
