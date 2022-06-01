package com.isa.fishingapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.isa.fishingapp.dto.ActionDTO;
import com.isa.fishingapp.dto.ReservableSearchDTO;
import com.isa.fishingapp.model.Action;
import com.isa.fishingapp.model.AvailableDateRange;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.Reservation;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.service.ActionService;
import com.isa.fishingapp.service.ReservableService;
import com.isa.fishingapp.service.ReservationService;
import com.isa.fishingapp.service.UserService;

public abstract class ReservableController<T extends Reservable, Y extends Reservation> {
	@Autowired
	ReservableService<T> reservableService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;
	@Autowired
	ActionService actionService;
	
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
		Reservable reservable = reservableService.findById(reservableId);
		if(reservable == null)
			return new ResponseEntity<>(
					null, 
					HttpStatus.NOT_FOUND);
		
		if(reservable.getAvailableDateRanges().isEmpty())
			return new ResponseEntity<>(
					null, 
					HttpStatus.OK);
		
		List<Reservation> foundReservations = reservationService.findByEntityIdAndCancelled(reservableId, false);
		List<Action> foundActions = actionService.findByReservable_IdAndAvailable(reservableId); // Actions are reserved via a separate system
		List<DateRange> occupiedDateRanges = new ArrayList<>();
		for(Reservation rl : foundReservations)
			occupiedDateRanges.add(rl.getDateRange());
		for(Action a : foundActions)
			occupiedDateRanges.add(a.getDateRange());
		
		List<DateRange> availableDateRanges = new ArrayList<>();
		for(AvailableDateRange a : reservable.getAvailableDateRanges()) {
			List<DateRange> tmpAvailableDateRanges = a.getRange().splitByDateRange(occupiedDateRanges);
			for(DateRange d : tmpAvailableDateRanges)
				availableDateRanges.add(d);
		}
		//DateRange maximumDateRange = new DateRange(LocalDateTime.now(), (LocalDateTime.now().plusYears(2)));
		return new ResponseEntity<>(
				availableDateRanges, 
				HttpStatus.OK);
	}
	
	@GetMapping("/reservations/{userId}")
	@PreAuthorize("#userId == authentication.principal.id")
	public ResponseEntity<List<Reservation>> getAllReservations(@PathVariable int userId)
	{
		return new ResponseEntity<>(
				reservationService.findByUserId(userId), 
				HttpStatus.OK);
	}
	
	@GetMapping("/reservations/actions/{reservableId}")
	@PreAuthorize("permitAll")
	public ResponseEntity<List<Action>> getAllActionReservations(@PathVariable int reservableId)
	{
		return new ResponseEntity<>(
				actionService.findByReservable_IdAndAvailable(reservableId), 
				HttpStatus.OK);
	}
	
	@PostMapping("/reservations/actions")
	@PreAuthorize("permitAll")
	public ResponseEntity<String> addAction(@RequestBody ActionDTO actionDTO) throws Exception
	{
		Action action = new Action();
		action.setDateRange(new DateRange(actionDTO.getFromDate(), actionDTO.getToDate()));
		action.setDiscount(actionDTO.getDiscount());
		action.setReservable(reservableService.findById(actionDTO.getReservableId()));
		if(action.getReservable() == null)
			return new ResponseEntity<>(
					"Reservable not found!", 
					HttpStatus.NOT_FOUND);
		actionService.save(action);
		return new ResponseEntity<>(
				"Action added!", 
				HttpStatus.OK);
	}
	
	@PutMapping("/reservations/{reservationId}/cancel")
	//@PreAuthorize("#userId == authentication.principal.id")
	public ResponseEntity<String> cancelReservation(@PathVariable int reservationId)
	{
		if(reservationService.cancelReservation(reservationId) != null)
			return new ResponseEntity<>(
					"Reservation successfully cancelled!", 
					HttpStatus.OK);
		return new ResponseEntity<>(
				"Reservation not found!", 
				HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/search/{page}")
	public ResponseEntity<Page<T>> getReservables(@RequestBody(required=false) ReservableSearchDTO searchParameters, @PathVariable int page)
	{
		Page<T> foundReservables;
		if(searchParameters != null && searchParameters.getSortType() != null && searchParameters.getSortDir() != null && searchParameters.getSortDir().equals("descending"))
			foundReservables = reservableService.findAll(searchParameters, PageRequest.of(page, 1, Sort.by(searchParameters.getSortType()).descending()));
		else if(searchParameters != null && searchParameters.getSortType() != null && searchParameters.getSortDir() != null)
			foundReservables = reservableService.findAll(searchParameters, PageRequest.of(page, 1, Sort.by(searchParameters.getSortType()).ascending()));
		else
			foundReservables = reservableService.findAll(searchParameters, PageRequest.of(page, 1));
		return new ResponseEntity<>(
					foundReservables, 
					HttpStatus.OK);
	}
	
	@GetMapping("/subscribers")
	@PreAuthorize("hasRole('CUSTOMER') and #userId == authentication.principal.id")
	public ResponseEntity<String> isSubscriberOfReservable(@RequestParam Integer userId, @RequestParam Integer reservableId)
	{
		if(reservableService.isUserSubscribed(userId, reservableId))
			return new ResponseEntity<>(
					"SUBSCRIBED", 
					HttpStatus.OK);
		return new ResponseEntity<>(
				"NOT_SUBSCRIBED", 
				HttpStatus.OK);
	}
	
	@PutMapping("/subscribers")
	@PreAuthorize("hasRole('CUSTOMER') and #userId == authentication.principal.id")
	public ResponseEntity<String> subscribeUserToReservable(@RequestParam Integer userId, @RequestParam Integer reservableId)
	{
		User user = userService.findById(userId);
		T reservable = reservableService.findById(reservableId);
		if(reservable == null || user == null)
			return new ResponseEntity<>(
					"NOT FOUND", 
					HttpStatus.NOT_FOUND);
		reservable.getSubscribers().add(user);
		reservableService.save(reservable);
		return new ResponseEntity<>(
				"Successfully subscribed!", 
				HttpStatus.OK);
	}
	
	@DeleteMapping("/subscribers")
	@PreAuthorize("hasRole('CUSTOMER') and #userId == authentication.principal.id")
	public ResponseEntity<String> unsubscribeUserToReservable(@RequestParam Integer userId, @RequestParam Integer reservableId)
	{
		User user = userService.findById(userId);
		T reservable = reservableService.findById(reservableId);
		if(reservable == null || user == null)
			return new ResponseEntity<>(
					"NOT FOUND", 
					HttpStatus.NOT_FOUND);
		reservable.getSubscribers().remove(user);
		reservableService.save(reservable);
		return new ResponseEntity<>(
				"Successfully unsubscribed!", 
				HttpStatus.OK);
	}
}
