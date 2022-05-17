package com.isa.fishingapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.Boat;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.ReservationBoat;
import com.isa.fishingapp.model.ReservableAmenity;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class BoatService extends ReservableService<Boat> {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationService reservationService;
	
	public BoatService() {
		super("BOAT");
	}
	
	public ResponseEntity<String> reserve(ReserveReservableDTO reserveBoatDTO)
	{
		try {
			if(!reservationService.findAllReservationsInInterval(reserveBoatDTO.getReservableId(), new DateRange(reserveBoatDTO.getFromDate(), reserveBoatDTO.getToDate())).isEmpty())
				return new ResponseEntity<>(
					      "Time period is already taken!", 
					      HttpStatus.BAD_REQUEST);
		} catch (Exception e1) {
			return new ResponseEntity<>(
				      "Date range is invalid!", 
				      HttpStatus.BAD_REQUEST);
		}
		ReservationBoat reservation;
		try {
			reservation = new ReservationBoat(userRepository.getById(reserveBoatDTO.getUserId()), new DateRange(reserveBoatDTO.getFromDate(), reserveBoatDTO.getToDate()), findById(reserveBoatDTO.getReservableId()));
			
			Set<ReservableAmenity> reservedAmenities = new HashSet<ReservableAmenity>();
			if(reserveBoatDTO.getAmenities() != null && !reserveBoatDTO.getAmenities().isEmpty()) {
				for(Integer i : reserveBoatDTO.getAmenities())
				{
					ReservableAmenity amenity = reservableRepository.findReservableAmenityById(i).orElse(null);
					if(amenity != null)
						reservedAmenities.add(amenity);
				}
			}
			reservation.setAmenities(reservedAmenities);
			
			reservationService.save(reservation);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<>(
			      "Lodging reservation successful!", 
			      HttpStatus.OK);
	}
}
