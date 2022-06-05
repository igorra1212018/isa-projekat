package com.isa.fishingapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservableAmenity;
import com.isa.fishingapp.model.ReservationLodging;
import com.isa.fishingapp.repository.ActionRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class LodgingService extends ReservableService<Lodging> {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private ReservationService reservationsService;
	
	public LodgingService() {
		super("LODGING");
	}
	
	public ResponseEntity<String> reserveLodging(ReserveReservableDTO reserveLodgingDTO)
	{
		try {
			if(!reservationsService.findAllReservationsInInterval(reserveLodgingDTO.getReservableId(), new DateRange(reserveLodgingDTO.getFromDate(), reserveLodgingDTO.getToDate())).isEmpty())
				return new ResponseEntity<>(
					      "Time period is already taken!", 
					      HttpStatus.BAD_REQUEST);
		} catch (Exception e1) {
			return new ResponseEntity<>(
				      "Date range is invalid!", 
				      HttpStatus.BAD_REQUEST);
		}
		ReservationLodging reservation;
		try {
			reservation = new ReservationLodging(userRepository.getById(reserveLodgingDTO.getUserId()), new DateRange(reserveLodgingDTO.getFromDate(), reserveLodgingDTO.getToDate()), findById(reserveLodgingDTO.getReservableId()));
			reservation.setPrice(reserveLodgingDTO.getPrice());
			if(reserveLodgingDTO.getActionId() != null)
				reservation.setAction(actionRepository.findById(reserveLodgingDTO.getActionId()).orElse(null));
			
			Set<ReservableAmenity> reservedAmenities = new HashSet<ReservableAmenity>();
			if(reserveLodgingDTO.getAmenities() != null && !reserveLodgingDTO.getAmenities().isEmpty()) {
				for(Integer i : reserveLodgingDTO.getAmenities())
				{
					ReservableAmenity amenity = reservableRepository.findReservableAmenityById(i).orElse(null);
					if(amenity != null)
						reservedAmenities.add(amenity);
				}
			}
			reservation.setAmenities(reservedAmenities);
			
			reservationsService.save(reservation);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<>(
			      "Lodging reservation successful!", 
			      HttpStatus.OK);
	}
}
