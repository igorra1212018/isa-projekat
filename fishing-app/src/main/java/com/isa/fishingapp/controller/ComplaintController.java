package com.isa.fishingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.ComplaintDTO;
import com.isa.fishingapp.model.Complaint;
import com.isa.fishingapp.model.Reservation;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.model.enums.ERequestApproval;
import com.isa.fishingapp.service.ComplaintService;
import com.isa.fishingapp.service.ReservationService;
import com.isa.fishingapp.service.UserService;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin("http://localhost:8081/")
public class ComplaintController {
	@Autowired
	ComplaintService complaintService;
	@Autowired
	UserService userService;
	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Complaint>> getComplaints(@RequestParam(required = false, name = "userId") Integer userId) {
	    if(userId == null)
	    	return new ResponseEntity<>(
					complaintService.findAll(), 
					HttpStatus.OK);
	    return new ResponseEntity<>(
				complaintService.findByUser_Id(userId), 
				HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("#complaint.userId == authentication.principal.id")
	public ResponseEntity<String> addComplaint(@RequestBody ComplaintDTO complaintDTO)
	{
		if(!complaintService.findByUser_IdAndReservation_Id(complaintDTO.getUserId(), complaintDTO.getReservationId()).isEmpty())
			return new ResponseEntity<>(
					"Complaint by this user for this entity already exists!", 
					HttpStatus.BAD_REQUEST);
		User user = userService.findById(complaintDTO.getUserId());
		Reservation reservation = reservationService.findById(complaintDTO.getReservationId());
		if (user == null || reservation == null) {
			return new ResponseEntity<>(
					"User or reservation does not exist!", 
					HttpStatus.NOT_FOUND);
		}
		Complaint complaint = new Complaint();
		complaint.setDescription(complaintDTO.getDescription());
		complaint.setUser(user);
		complaint.setReservation(reservation);
		complaint.setApproved(ERequestApproval.PENDING);
		complaintService.save(complaint);
		return new ResponseEntity<>(
			      "Complaint added!", 
			      HttpStatus.OK);
	}
}
