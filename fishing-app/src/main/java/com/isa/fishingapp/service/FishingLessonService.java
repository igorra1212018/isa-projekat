package com.isa.fishingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.FishingLesson;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationFishingLesson;
import com.isa.fishingapp.model.ReservationLodging;
import com.isa.fishingapp.repository.ReservationLodgingRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class FishingLessonService extends ReservableService<FishingLesson> {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationService<ReservationFishingLesson> reservationsService;
	
	public FishingLessonService() {
		super("FISHING_LESSON");
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
		ReservationFishingLesson reservation;
		try {
			reservation = new ReservationFishingLesson(userRepository.getById(reserveBoatDTO.getUserId()), new DateRange(reserveBoatDTO.getFromDate(), reserveBoatDTO.getToDate()), findById(reserveBoatDTO.getReservableId()));
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

