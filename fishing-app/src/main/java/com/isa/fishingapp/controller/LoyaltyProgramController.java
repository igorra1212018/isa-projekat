package com.isa.fishingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.UserDTO;
import com.isa.fishingapp.model.LoyaltyLevel;
import com.isa.fishingapp.repository.LoyaltyLevelRepository;

@RestController
@RequestMapping("/api/loyaltyProgram")
@CrossOrigin("http://localhost:8081/")
public class LoyaltyProgramController {
	
	@Autowired
	LoyaltyLevelRepository loyaltyLevelRepository;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<List<LoyaltyLevel>> getAllLevels() {
		
		return new ResponseEntity<List<LoyaltyLevel>>(
				loyaltyLevelRepository.findAll(),
				HttpStatus.OK);
    }
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> deleteLevel(int id) {
		
		loyaltyLevelRepository.deleteById(id);
		
		return new ResponseEntity<String>(
				"Deleted!",
				HttpStatus.OK);
    }
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<LoyaltyLevel> addLevel(@RequestBody LoyaltyLevel level) {
	
		return new ResponseEntity<LoyaltyLevel>(
				loyaltyLevelRepository.save(level),
				HttpStatus.OK);
    }
	
	@PutMapping("/changeUserPointsAdd/{points}")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> changeUserPointsAdd(@PathVariable int points) {
		
		List<LoyaltyLevel> all = loyaltyLevelRepository.findAll();
		for(LoyaltyLevel l : all){
			l.setUserPointsAdd(points);
		}
		loyaltyLevelRepository.saveAll(all);
		
		return new ResponseEntity<String>(
				HttpStatus.OK);
    }
	
	@PutMapping("/changeOwnerPointsAdd/{points}")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> changeOwnerPointsAdd(@PathVariable int points) {
		
		List<LoyaltyLevel> all = loyaltyLevelRepository.findAll();
		for(LoyaltyLevel l : all){
			l.setOwnerPointsAdd(points);
		}
		loyaltyLevelRepository.saveAll(all);
		
		return new ResponseEntity<String>(
				HttpStatus.OK);
    }

	
}
