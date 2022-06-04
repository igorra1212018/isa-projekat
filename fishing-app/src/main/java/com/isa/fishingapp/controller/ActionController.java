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
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.ActionDTO;
import com.isa.fishingapp.model.Action;
import com.isa.fishingapp.model.FishingLesson;
import com.isa.fishingapp.model.LoyaltyLevel;
import com.isa.fishingapp.repository.ActionRepository;
import com.isa.fishingapp.repository.LoyaltyLevelRepository;
import com.isa.fishingapp.repository.ReservableRepository;

@RestController
@RequestMapping("/api/action")
@CrossOrigin("http://localhost:8081/")
public class ActionController {
	
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	ReservableRepository<FishingLesson> reservableRepository;
	
	@PostMapping("/add")
    public ResponseEntity<String> addAction(@RequestBody ActionDTO actionDTO) {
		
		try {
			Action action = new Action(actionDTO);
			action.setReservable(reservableRepository.getById(actionDTO.getReservableId()));
			actionRepository.save(action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(
				"Action added!",
				HttpStatus.OK);
    }

}
