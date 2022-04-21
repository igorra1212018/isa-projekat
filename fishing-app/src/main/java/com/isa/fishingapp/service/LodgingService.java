package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.LodgingSearchDTO;
import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationLodging;
import com.isa.fishingapp.repository.LodgingRepository;
import com.isa.fishingapp.repository.ReservationLodgingRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class LodgingService {
	@Autowired
	private LodgingRepository lodgingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationLodgingRepository reservationLodgingRepository;
	
	public Lodging registerLodging(Lodging lodging)
	{
		return lodgingRepository.save(lodging);
	}
	
	public Lodging findLodgingById(Integer id)
	{
		return lodgingRepository.findById(id).orElse(null);
	}
	
	public List<Lodging> getAllLodgings()
	{
		return lodgingRepository.findAll();
	}
	
	public List<Lodging> getAllLodgings(LodgingSearchDTO searchParameters)
	{
		return lodgingRepository.getLodgingsByName(searchParameters.getName());
	}
	
	public List<ReservationLodging> getReservationsForLodging(int lodgingId)
	{
		return reservationLodgingRepository.getReservationLodgings(lodgingId);
	}
	
	public ResponseEntity<String> reserveLodging(ReserveLodgingDTO reserveLodgingDTO)
	{
		try {
			if(!getAllReservationsInInterval(reserveLodgingDTO.getLodgingId(), new DateRange(reserveLodgingDTO.getFromDate(), reserveLodgingDTO.getToDate())).isEmpty())
				return new ResponseEntity<>(
					      "Time period is already taken!", 
					      HttpStatus.BAD_REQUEST);
		} catch (Exception e1) {
			return new ResponseEntity<>(
				      "Date range is invalid!", 
				      HttpStatus.BAD_REQUEST);
		}
		ReservationLodging reservationLodging = new ReservationLodging();
		reservationLodging.setCancelled(false);
		try {
			reservationLodging.setDateRange(new DateRange(reserveLodgingDTO.getFromDate(), reserveLodgingDTO.getToDate()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		reservationLodging.setLodging(findLodgingById(reserveLodgingDTO.getLodgingId()));
		reservationLodging.setUser(userRepository.getById(reserveLodgingDTO.getUserId()));
		reservationLodgingRepository.save(reservationLodging);
		return new ResponseEntity<>(
			      "Lodging reservation successful!", 
			      HttpStatus.OK);
	}
	
	public List<ReservationLodging> getAllReservationsInInterval(Integer lodgingId, DateRange dateRange)
	{
		return reservationLodgingRepository.getReservationLodgingsInInterval(lodgingId, dateRange.getFromDate(), dateRange.getToDate());
	}

	public Lodging getLodging(int lodgingId) {
		return lodgingRepository.findById(lodgingId).orElse(null);
	}
}
