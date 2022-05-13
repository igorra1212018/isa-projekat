package com.isa.fishingapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.isa.fishingapp.dto.ReservableSearchDTO;
import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.Reservation;
import com.isa.fishingapp.service.ReservableService;
import com.isa.fishingapp.service.ReservationService;

public abstract class ReservableController<T extends Reservable, Y extends Reservation> {
	@Autowired
	ReservableService<T> reservableService;
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/all")
	@PreAuthorize("permitAll")
	public List<T> getAllReservables(Model model)
	{
		return reservableService.findAll();
	}
	
	@GetMapping("/{reservableId}")
	@PreAuthorize("permitAll")
	public T getReservableById(@PathVariable int reservableId)
	{
		return reservableService.findById(reservableId);
	}
	
	@GetMapping("/get_available_reservation_dates/{reservableId}")
	public ResponseEntity<List<DateRange>> getAvailableLodgingReservationDates(@PathVariable int reservableId) throws Exception
	{
		List<Reservation> foundReservations = reservationService.findByEntityId(reservableId);
		List<DateRange> occupiedDateRanges = new ArrayList<>();
		for(Reservation rl : foundReservations)
			occupiedDateRanges.add(rl.getDateRange());
		DateRange maximumDateRange = new DateRange(LocalDateTime.now(), (LocalDateTime.now().plusYears(2)));
		return new ResponseEntity<>(
				maximumDateRange.splitByDateRange(occupiedDateRanges), 
				HttpStatus.OK);
	}
	
	/*@GetMapping("/reservations/{reservableId}")
	public ResponseEntity<List<ReserveReservableDTO>> getReservationsForReservable(@PathVariable int reservableId)
	{
		List<T> foundReservations = reservableService.findByReservableId(reservableId);
		List<ReserveReservableDTO> foundReservationsDTO = ReserveReservableDTO.convertReservationListToDTO(foundReservations);
		return new ResponseEntity<>(
				foundReservationsDTO, 
				HttpStatus.OK);
	}*/
	
	@GetMapping("/reservations/{userId}")
	//@PreAuthorize("#userId == authentication.principal.id")
	public ResponseEntity<List<Reservation>> getAllReservables(@PathVariable int userId)
	{
		return new ResponseEntity<>(
				reservationService.findByUserId(userId), 
				HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<T>> getLodgings(@RequestBody ReservableSearchDTO searchParameters)
	{
		List<T> foundReservables = reservableService.findAll(searchParameters);
		return new ResponseEntity<>(
					foundReservables, 
					HttpStatus.OK);
	}
}
