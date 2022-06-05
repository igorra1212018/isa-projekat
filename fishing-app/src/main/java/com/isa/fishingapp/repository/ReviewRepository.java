package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByUser_Id(Integer userId);
	List<Review> findByUser_IdAndReservable_Id(Integer userId, Integer reservableId);
}
