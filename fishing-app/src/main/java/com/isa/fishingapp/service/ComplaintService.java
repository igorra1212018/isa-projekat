package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Complaint;
import com.isa.fishingapp.model.UserCreationRequest;
import com.isa.fishingapp.model.enums.ERequestApproval;
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

	public ResponseEntity<String> approveRequest(int id) {
		Complaint complaint = complaintRepository.getById(id);
		complaint.setApproved(ERequestApproval.APPROVED);
		complaintRepository.save(complaint);
		
		return new ResponseEntity<>(
			      "Complaint approved!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> rejectRequest(int id, String description) {
		
		description.substring(0, description.length() - 1);
		System.out.println(description);
		Complaint complaint = complaintRepository.getById(id);
		complaint.setResponse(description);
		complaint.setApproved(ERequestApproval.REJECTED);
		complaintRepository.save(complaint);
		
		return new ResponseEntity<>(
			      "Complaint rejected!", 
			      HttpStatus.OK);
	}
}
