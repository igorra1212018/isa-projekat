package com.isa.fishingapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.service.LodgingService;
import com.isa.fishingapp.dto.LodgingSearchDTO;
import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationLodging;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4000/")
public class LodgingsController {
	@Autowired
	LodgingService lodgingService;
	
	@GetMapping("/lodgings")
	//@PreAuthorize("hasRole('CUSTOMER')")
	@PreAuthorize("permitAll")
	public List<Lodging> getLodgings(Model model)
	{
		return lodgingService.getAllLodgings();
	}
	
	@GetMapping("/lodging/{lodgingId}")
	public Lodging getLodging(@PathVariable int lodgingId)
	{
		return lodgingService.getLodging(lodgingId);
	}
	
	@PostMapping("/search_lodgings")
	public ResponseEntity<List<Lodging>> getLodgings(@RequestBody LodgingSearchDTO searchParameters)
	{
		List<Lodging> foundLodgings = lodgingService.getAllLodgings(searchParameters);
		return new ResponseEntity<>(
					foundLodgings, 
					HttpStatus.OK);
	}
	
	@GetMapping("/get_reservations_for_lodging/{lodgingId}")
	public ResponseEntity<List<ReserveLodgingDTO>> getReservationsForLodging(@PathVariable int lodgingId)
	{
		List<ReservationLodging> foundReservations = lodgingService.getReservationsForLodging(lodgingId);
		List<ReserveLodgingDTO> foundReservationsDTO = ReserveLodgingDTO.convertReservationLodgingListToDTO(foundReservations);
		return new ResponseEntity<>(
				foundReservationsDTO, 
				HttpStatus.OK);
	}
	
	@GetMapping("/get_available_lodging_reservation_dates/{lodgingId}")
	public ResponseEntity<List<DateRange>> getAvailableLodgingReservationDates(@PathVariable int lodgingId) throws Exception
	{
		List<ReservationLodging> foundReservations = lodgingService.getReservationsForLodging(lodgingId);
		List<DateRange> occupiedDateRanges = new ArrayList<>();
		for(ReservationLodging rl : foundReservations)
			occupiedDateRanges.add(rl.getDateRange());
		DateRange maximumDateRange = new DateRange(LocalDateTime.now(), (LocalDateTime.now().plusYears(2)));
		return new ResponseEntity<>(
				maximumDateRange.splitByDateRange(occupiedDateRanges), 
				HttpStatus.OK);
	}
	
	@PostMapping("/reserve_lodging")
	public ResponseEntity<String> reserveLodging(@RequestBody ReserveLodgingDTO reservationParameters)
	{
		return lodgingService.reserveLodging(reservationParameters);
	}
}
