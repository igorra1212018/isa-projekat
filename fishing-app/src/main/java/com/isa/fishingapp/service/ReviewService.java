package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Review;
import com.isa.fishingapp.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	public List<Review> findByUser_IdAndReservable_Id(Integer userId, Integer reservableId) {
		return reviewRepository.findByUser_IdAndReservable_Id(userId, reservableId);
	}
	
	public void save(Review review) {
		reviewRepository.save(review);
	}

	public ResponseEntity<String> approveRequest(int id) {
		Review r = reviewRepository.findById(null).orElse(null);
		if (r != null){
			r.setApproved(true);
		}
		
		return new ResponseEntity<>(
			      "Request approved!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> rejectRequest(int id) {
		Review r = reviewRepository.findById(null).orElse(null);
		if (r != null){
			r.setApproved(false);
		}
		
		return new ResponseEntity<>(
			      "Request rejected!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<List<Review>> getAll() {
		
		return new ResponseEntity<>(
			      reviewRepository.findAll(), 
			      HttpStatus.OK);
	}
}
