package com.isa.fishingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.service.FishingLessonService;
import com.isa.fishingapp.service.ReservationService;
import com.isa.fishingapp.dto.EditFishingLessonDTO;
import com.isa.fishingapp.dto.FishingLessonDTO;
import com.isa.fishingapp.dto.ReserveReservableDTO;
import com.isa.fishingapp.model.FishingLesson;
import com.isa.fishingapp.model.ReservationFishingLesson;

@RestController
@RequestMapping("/api/fishinglesson")
@CrossOrigin("http://localhost:4000/")
public class FishingLessonController extends ReservableController<FishingLesson, ReservationFishingLesson> {
	@Autowired
	FishingLessonService fishingLessonService;
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/reserve")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public ResponseEntity<String> reserveFishingLesson(@RequestBody ReserveReservableDTO reservationParameters)
	{
		return fishingLessonService.reserve(reservationParameters);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<String> addFishingLesson(@RequestBody FishingLessonDTO fishingLesson)
	{
		System.out.println(fishingLesson.getUserId());
		return fishingLessonService.add(fishingLesson);
	}
	
	@PutMapping("/edit")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<String> editFishingLesson(@RequestBody EditFishingLessonDTO fishingLesson)
	{
		return fishingLessonService.edit(fishingLesson);
	}
}
