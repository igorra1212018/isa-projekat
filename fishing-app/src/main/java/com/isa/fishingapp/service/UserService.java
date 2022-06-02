package com.isa.fishingapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.model.Role;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.model.UserCreationRequest;
import com.isa.fishingapp.model.VerificationToken;
import com.isa.fishingapp.model.enums.ERole;
import com.isa.fishingapp.repository.RoleRepository;
import com.isa.fishingapp.repository.TokenRepository;
import com.isa.fishingapp.repository.UserCreationRequestRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCreationRequestRepository userCreationRequestRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TokenRepository tokenRepository;
	
	public User registerUser(User user)
	{
		Role userRole = roleRepository.findByName(ERole.CUSTOMER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		user.getRoles().add(userRole);
		return userRepository.save(user);
	}
	
	public User save(User user)
	{
		return userRepository.save(user);
	}
	
	public User registerUser(User user, UserCreationRequest creationRequest)
	{
		Role userRole = roleRepository.findByName(ERole.LODGING_OWNER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		user.getRoles().add(userRole);
		userRepository.save(user);
		userCreationRequestRepository.save(creationRequest);
		return user;
	}
	
	public ResponseEntity<String> updateUser(UserProfileChangeDTO user)
	{
		User userToUpdate = findById(user.getId());
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
		boolean wasUserActivated = userToUpdate.isActivated();
		String oldPassword = "";
		if(user.getNewPassword() == null || user.getNewPassword().isBlank())
			oldPassword = userToUpdate.getPassword();
		userToUpdate = new User(user);
		if(!oldPassword.isBlank())
			userToUpdate.setPassword(oldPassword);
		userToUpdate.setActivated(wasUserActivated);
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
	
	public User findById(int id)
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

	public boolean isEmailAvailable(String email) {
		return (userRepository.findByEmail(email) == null);
	}
	
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken).orElse(null);
        if(vToken == null)
        	return null;
        vToken.updateToken(UUID.randomUUID()
            .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }
}
