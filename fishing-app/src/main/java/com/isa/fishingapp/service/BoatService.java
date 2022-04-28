package com.isa.fishingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.Boat;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.ReservationBoat;
import com.isa.fishingapp.repository.BoatRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class BoatService extends ReservableService<Boat> {
	@Autowired
	private BoatRepository boatRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationService<ReservationBoat> reservationsService;
	
	public BoatService() {
		super("BOAT");
	}
	
	public Boat registerBoat(Boat boat)
	{
		return boatRepository.save(boat);
	}
	
	public ResponseEntity<String> reserve(ReserveReservableDTO reserveBoatDTO)
	{
		try {
			if(!reservationsService.findAllReservationsInInterval(reserveBoatDTO.getReservableId(), new DateRange(reserveBoatDTO.getFromDate(), reserveBoatDTO.getToDate())).isEmpty())
				return new ResponseEntity<>(
					      "Time period is already taken!", 
					      HttpStatus.BAD_REQUEST);
		} catch (Exception e1) {
			return new ResponseEntity<>(
				      "Date range is invalid!", 
				      HttpStatus.BAD_REQUEST);
		}
		ReservationBoat reservationLodging;
		try {
			reservationLodging = new ReservationBoat(userRepository.getById(reserveBoatDTO.getUserId()), new DateRange(reserveBoatDTO.getFromDate(), reserveBoatDTO.getToDate()), findById(reserveBoatDTO.getReservableId()));
			reservationsService.save(reservationLodging);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<>(
			      "Lodging reservation successful!", 
			      HttpStatus.OK);
	}
}
