package com.isa.fishingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.model.Owner;
import com.isa.fishingapp.model.Role;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.model.enums.ERole;
import com.isa.fishingapp.repository.OwnerRepository;
import com.isa.fishingapp.repository.RoleRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public User registerUser(User user)
	{
		Role userRole = roleRepository.findByName(ERole.CUSTOMER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		user.getRoles().add(userRole);
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
	
	public User registerOwner(Owner owner)
	{
		return ownerRepository.save(owner);
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
	
	public User findByEmail(String email)
	{
		Optional<User> foundUser = userRepository.findByEmail(email);
		if(foundUser.isEmpty())
			return null;
		else
			return foundUser.get();
	}
}
