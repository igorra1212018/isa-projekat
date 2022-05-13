package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.repository.ReservationRepository;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservation;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationsRepository;
	private String discriminatorString;
	
	public ReservationService() {
	}
	
	public ReservationService(String discriminatorString) {
		this.discriminatorString = discriminatorString;
	}
	
	public List<Reservation> findByEntityId(int entityId)
	{
		return reservationsRepository.findByEntityId(entityId);
	}
	
	public List<Reservation> findAllReservationsInInterval(Integer entityId, DateRange dateRange)
	{
		return reservationsRepository.findReservationEntitiesInInterval(entityId, dateRange.getFromDate(), dateRange.getToDate());
	}
	
	public List<Reservation> findByUserId(Integer userId)
	{
		return reservationsRepository.findByUserId(userId);
	}

	public void save(Reservation reservation) {
		reservationsRepository.save(reservation);
	}
}
