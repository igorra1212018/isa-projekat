package com.isa.fishingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationLodging;
import com.isa.fishingapp.repository.LodgingRepository;
import com.isa.fishingapp.repository.ReservationLodgingRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class LodgingService extends ReservableService<Lodging> {
	@Autowired
	private LodgingRepository lodgingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationService<ReservationLodging> reservationsService;
	@Autowired
	private ReservationLodgingRepository reservationLodgingRepository;
	
	public LodgingService() {
		super("LODGING");
	}
	
	public Lodging registerLodging(Lodging lodging)
	{
		return lodgingRepository.save(lodging);
	}
	
	public ResponseEntity<String> reserveLodging(ReserveLodgingDTO reserveLodgingDTO)
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
		ReservationLodging reservationLodging;
		try {
			reservationLodging = new ReservationLodging(userRepository.getById(reserveLodgingDTO.getUserId()), new DateRange(reserveLodgingDTO.getFromDate(), reserveLodgingDTO.getToDate()), findById(reserveLodgingDTO.getReservableId()));
			reservationLodgingRepository.save(reservationLodging);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ResponseEntity<>(
			      "Lodging reservation successful!", 
			      HttpStatus.OK);
	}
}
