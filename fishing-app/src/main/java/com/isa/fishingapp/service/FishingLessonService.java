package com.isa.fishingapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.FishingLessonDTO;
import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.FishingLesson;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservableAmenity;
import com.isa.fishingapp.model.ReservationFishingLesson;
import com.isa.fishingapp.model.ReservationLodging;
import com.isa.fishingapp.repository.ReservationLodgingRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class FishingLessonService extends ReservableService<FishingLesson> {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReservationService reservationService;
	
	public FishingLessonService() {
		super("FISHING_LESSON");
	}
	
	public ResponseEntity<String> reserve(ReserveReservableDTO reserveReservableDTO)
	{
		try {
			if(!reservationService.findAllReservationsInInterval(reserveReservableDTO.getReservableId(), new DateRange(reserveReservableDTO.getFromDate(), reserveReservableDTO.getToDate())).isEmpty())
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
			reservation = new ReservationFishingLesson(userRepository.getById(reserveReservableDTO.getUserId()), new DateRange(reserveReservableDTO.getFromDate(), reserveReservableDTO.getToDate()), findById(reserveReservableDTO.getReservableId()));
			
			Set<ReservableAmenity> reservedAmenities = new HashSet<ReservableAmenity>();
			if(reserveReservableDTO.getAmenities() != null && !reserveReservableDTO.getAmenities().isEmpty()) {
				for(Integer i : reserveReservableDTO.getAmenities())
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

	public ResponseEntity<String> add(FishingLessonDTO fishingLesson) {
		FishingLesson lesson = new FishingLesson(fishingLesson);
		lesson.setOwner(userRepository.getById(Integer.parseInt(fishingLesson.getUserId())));
		reservableRepository.save(lesson);
		System.out.println(lesson);
		System.out.println("------------TU SAM ----------------");
		return new ResponseEntity<>(
			      "Fishing lesson added successfully!", 
			      HttpStatus.OK);
	}
}

