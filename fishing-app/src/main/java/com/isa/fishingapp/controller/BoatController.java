package com.isa.fishingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.service.BoatService;
import com.isa.fishingapp.service.ReservationService;
import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.Boat;
import com.isa.fishingapp.model.ReservationBoat;
import com.isa.fishingapp.model.ReservationLodging;

@RestController
@RequestMapping("/api/boat")
@CrossOrigin("http://localhost:4000/")
public class BoatController extends ReservableController<Boat, ReservationBoat> {
	@Autowired
	BoatService boatService;
	@Autowired
	ReservationService<ReservationLodging> reservationService;
	
	@PostMapping("/reserve")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public ResponseEntity<String> reserveBoat(@RequestBody ReserveReservableDTO reservationParameters)
	{
		return boatService.reserve(reservationParameters);
	}
}
