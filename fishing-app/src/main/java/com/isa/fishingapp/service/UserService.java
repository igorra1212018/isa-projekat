package com.isa.fishingapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.OwnerDTO;
import com.isa.fishingapp.dto.UserDeletionRequestDTO;
import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.model.Country;
import com.isa.fishingapp.model.Location;
import com.isa.fishingapp.model.Role;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.model.UserCreationRequest;
import com.isa.fishingapp.model.UserDeletionRequest;
import com.isa.fishingapp.model.VerificationToken;
import com.isa.fishingapp.model.enums.ERequestApproval;
import com.isa.fishingapp.model.enums.ERole;
import com.isa.fishingapp.repository.CountryRepository;
import com.isa.fishingapp.repository.RoleRepository;
import com.isa.fishingapp.repository.TokenRepository;
import com.isa.fishingapp.repository.UserCreationRequestRepository;
import com.isa.fishingapp.repository.UserDeletionRequestRepository;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCreationRequestRepository userCreationRequestRepository;
	@Autowired
	private UserDeletionRequestRepository userDeletionRequestRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	
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
//		Role userRole = roleRepository.findByName(ERole.LODGING_OWNER)
//				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//		user.getRoles().add(userRole);
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
		if(user.getNewPassword() != null && !user.getNewPassword().isBlank()) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getOldPasswordGuess()));
			user.setNewPassword(encoder.encode(user.getNewPassword()));
		}
		String oldPassword = "";
		if(user.getNewPassword() == null || user.getNewPassword().isBlank())
			oldPassword = userToUpdate.getPassword();
		userToUpdate.setResidence(user.getResidence());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setContactPhone(user.getContactPhone());
		if(!oldPassword.isBlank())
			userToUpdate.setPassword(oldPassword);
		userRepository.save(userToUpdate);
		return new ResponseEntity<>(
			      "Profile edit successful!", 
			      HttpStatus.OK);
	}
	
	public User authenticate(String email, String password)
	{
		User user = userRepository.findByEmailAndPassword(email, password).orElse(null);
		if(user != null && !user.isActivated())
			return null;
		return user;
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
    
    public void changeActivation(String email) {
    	email = email.replace("%40", "@");
    	email = email.replace("=", "");
    	User user = userRepository.findByEmail(email).get();
    	user.setActivated(!user.isActivated());
    	userRepository.save(user);
    }

	public List<UserCreationRequest> getAllUserCreationRequests() {
	
		return userCreationRequestRepository.findAll();
	}

	public ResponseEntity<String> approveRequest(int id) {
		UserCreationRequest request = userCreationRequestRepository.getById(id);
		request.setRequestApproval(ERequestApproval.APPROVED);
		request.getUser().setActivated(true);
		userCreationRequestRepository.save(request);
		
		return new ResponseEntity<>(
			      "Request approved!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> rejectRequest(int id, String description) {
		
		description.substring(0, description.length() - 1);
		System.out.println(description);
		UserCreationRequest request = userCreationRequestRepository.getById(id);
		request.setRejectionResponse(description);
		request.setRequestApproval(ERequestApproval.REJECTED);
		userCreationRequestRepository.save(request);
		
		return new ResponseEntity<>(
			      "Request rejected!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> registerAdmin(OwnerDTO user) {
		
		
		if (userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
			System.out.println(user.getEmail());
			return ResponseEntity
					.badRequest()
					.body("Error: Email is already taken!");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		User createdUser = new User(user);
		Country country = countryRepository.findById(user.getCountry().getId()).orElse(null);
		if(country == null)
			return ResponseEntity
					.badRequest()
					.body("Error: Country does not exist!");
		createdUser.setResidence(new Location(user.getAddress(), user.getCity(), country, 0, 0));

		createdUser.addRole(roleRepository.findByName(ERole.ADMINISTRATOR).get());
			
		userRepository.save(createdUser);
//		String appUrl = request.getContextPath();
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(createdUser, 
//          request.getLocale(), appUrl));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	public ResponseEntity<?> requestDeletion(UserDeletionRequestDTO deletionRequest) {
		User user = userRepository.findById(deletionRequest.getUserId()).orElse(null);
		if(user == null)
			return ResponseEntity
					.badRequest()
					.body("Error: User not found!");
		UserDeletionRequest deletionRequestObject = new UserDeletionRequest();
		deletionRequestObject.setRequestApproval(ERequestApproval.PENDING);
		deletionRequestObject.setRequestDescription(deletionRequest.getRequestDescription());
		deletionRequestObject.setUser(user);
		userDeletionRequestRepository.save(deletionRequestObject);
		return ResponseEntity
				.ok()
				.body("Deletion request created!");
	}
	
	public ResponseEntity<?> retractDeletion(int requestId) {
		UserDeletionRequest deletionRequest = userDeletionRequestRepository.findById(requestId).orElse(null);
		if(deletionRequest == null)
			return ResponseEntity
					.badRequest()
					.body("Error: Request not found!");
		userDeletionRequestRepository.deleteById(requestId);
		return ResponseEntity
				.ok()
				.body("Deletion request withdrawn!");
	}

	public ResponseEntity<String> approveDeletionRequest(int id) {
		UserDeletionRequest request = userDeletionRequestRepository.getById(id);
		request.setRequestApproval(ERequestApproval.APPROVED);
		request.getUser().setActivated(false);
		userDeletionRequestRepository.save(request);
		
		return new ResponseEntity<>(
			      "Request approved!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> rejectDeletionRequest(int id, String description) {
		
		description.substring(0, description.length() - 1);
		System.out.println(description);
		UserDeletionRequest request = userDeletionRequestRepository.getById(id);
		request.setRejectionResponse(description);
		request.setRequestApproval(ERequestApproval.REJECTED);
		userDeletionRequestRepository.save(request);
		
		return new ResponseEntity<>(
			      "Request rejected!", 
			      HttpStatus.OK);
	}

	public List<UserDeletionRequest> getAllUserDeletionRequests() {
		
		return userDeletionRequestRepository.findAll();
	}
}
