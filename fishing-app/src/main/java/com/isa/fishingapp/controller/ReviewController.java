package com.isa.fishingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.ReviewDTO;
import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.Review;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.service.ReservableService;
import com.isa.fishingapp.service.ReviewService;
import com.isa.fishingapp.service.UserService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("http://localhost:8081/")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	@Autowired
	UserService userService;
	@Autowired
	ReservableService<Reservable> reservableService;

	@PostMapping("/add")
	//@PreAuthorize("#reviewDTO.userId == authentication.principal.id")
	public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO)
	{
		if(!reviewService.findByUser_IdAndReservable_Id(reviewDTO.getUserId(), reviewDTO.getReservableId()).isEmpty())
			return new ResponseEntity<>(
					"Review by this user for this entity already exists!", 
					HttpStatus.BAD_REQUEST);
		User user = userService.findById(reviewDTO.getUserId());
		Reservable reservable = reservableService.findById(reviewDTO.getReservableId());
		if (user == null || reservable == null) {
			return new ResponseEntity<>(
					"User or reservable does not exist!", 
					HttpStatus.NOT_FOUND);
		}
		System.out.println("GOT HERE 1");
		Review review = new Review();
		System.out.println("GOT HERE 2");
		review.setDescription(reviewDTO.getDescription());
		review.setUser(user);
		review.setReservable(reservable);
		review.setRating(reviewDTO.getRating());
		reviewService.save(review);
		return new ResponseEntity<>(
			      "Review added!", 
			      HttpStatus.OK);
	}
}
