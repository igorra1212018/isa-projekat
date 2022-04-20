package com.isa.fishingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Lodging> getLodgings(Model model)
	{
		return lodgingService.getAllLodgings();
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
	
	@PostMapping("/reserve_lodging")
	public ResponseEntity<String> reserveLodging(@RequestBody ReserveLodgingDTO reservationParameters)
	{
		return lodgingService.reserveLodging(reservationParameters);
	}
}
