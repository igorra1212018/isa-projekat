package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.repository.ReservationRepository;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservation;

@Service
public class ReservationService<T extends Reservation> {
	@Autowired
	private ReservationRepository<T> reservationsRepository;
	
	public List<T> findByEntityId(int entityId)
	{
		return reservationsRepository.findByEntityId(entityId);
	}
	
	public List<T> findAllReservationsInInterval(Integer entityId, DateRange dateRange)
	{
		System.out.println(entityId);
		return reservationsRepository.findReservationEntitiesInInterval(entityId, dateRange.getFromDate(), dateRange.getToDate());
	}
}
