package com.isa.fishingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user)
	{
		return userRepository.save(user);
	}
	
	public ResponseEntity<String> updateUser(UserProfileChangeDTO user)
	{
		User userToUpdate = getUserById(user.getId());
		if(userToUpdate == null) {
			return new ResponseEntity<>(
				      "Profile not found!", 
				      HttpStatus.NOT_FOUND);
		}
		if(user.getNewPassword() != null && !user.getNewPassword().isBlank() && !userToUpdate.getPassword().equals(user.getOldPasswordGuess())) {
			return new ResponseEntity<>(
				      "Not Authorized", 
				      HttpStatus.UNAUTHORIZED);
		}
		String oldPassword = "";
		if(user.getNewPassword() == null || user.getNewPassword().isBlank())
			oldPassword = userToUpdate.getPassword();
		userToUpdate = new User(user);
		if(!oldPassword.isBlank())
			userToUpdate.setPassword(oldPassword);
		userRepository.save(userToUpdate);
		return new ResponseEntity<>(
			      "Profile edit successful!", 
			      HttpStatus.OK);
	}
	
	public User authenticate(String email, String password)
	{
		return userRepository.findByEmailAndPassword(email, password).orElse(null);
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User getUserById(int id)
	{
		Optional<User> foundUser = userRepository.findById(id);
		if(foundUser.isEmpty())
			return null;
		else
			return foundUser.get();
	}
}
