package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
