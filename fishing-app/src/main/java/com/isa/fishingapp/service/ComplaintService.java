package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Complaint;
import com.isa.fishingapp.repository.ComplaintRepository;

@Service
public class ComplaintService {
	@Autowired
	private ComplaintRepository complaintRepository;
	
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	
	public List<Complaint> findByUser_Id(Integer userId) {
		return complaintRepository.findByUser_Id(userId);
	}
	
	public List<Complaint> findByUser_IdAndReservation_Id(Integer userId, Integer reservationId) {
		return complaintRepository.findByUser_IdAndReservation_Id(userId, reservationId);
	}
	
	public void save(Complaint complaint) {
		complaintRepository.save(complaint);
	}
}
