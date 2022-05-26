package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	List<Complaint> findByUser_Id(Integer userId);
	List<Complaint> findByUser_IdAndReservation_Id(Integer userId, Integer reservationId);
	
}
