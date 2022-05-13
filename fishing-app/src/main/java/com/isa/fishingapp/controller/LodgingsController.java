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
import com.isa.fishingapp.service.ReservationService;
import com.isa.fishingapp.dto.LodgingSearchDTO;
import com.isa.fishingapp.dto.ReserveLodgingDTO;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationLodging;

@RestController
@RequestMapping("/api/lodging")
@CrossOrigin("http://localhost:4000/")
public class LodgingsController extends ReservableController<Lodging, ReservationLodging> {
	@Autowired
	LodgingService lodgingService;
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/reserve")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public ResponseEntity<String> reserveLodging(@RequestBody ReserveLodgingDTO reservationParameters)
	{
		return lodgingService.reserveLodging(reservationParameters);
	}
}
